package com.m08.uservalidation;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

public class Connection {
    // Define constants for the server and endpoints
    private static final String protocol_JOO = "http://";
    private static final String serverIP_JOO = "192.168.0.16";
    private static final String userValidationEndPoint_JOO = "/validacuenta.php";
    private static final String allDBUsersEndPoint_JOO = "/consultausuarios.php";

    // Method to validate a user
    protected static String validateUser(String user_JOO, String password_JOO) {
        return CompletableFuture.supplyAsync(() -> getValidateUser(user_JOO, password_JOO)).join();
    }

    // Method to retrieve all users
    protected static ArrayList<String> allUsers() {
        return CompletableFuture.supplyAsync(Connection::getAllUsers).join();
    }

    // Helper method to fetch all users from the server
    private static ArrayList<String> getAllUsers() {
        String apiUrl_JOO = protocol_JOO + serverIP_JOO + allDBUsersEndPoint_JOO;
        ArrayList<String> allUsers_JOO = new ArrayList<>();

        try {
            // Open a connection to the server
            HttpURLConnection connection_JOO = openConnection(apiUrl_JOO);
            connection_JOO.setConnectTimeout(2000);
            int responseCode_JOO = connection_JOO.getResponseCode();
            if (responseCode_JOO == 200) {
                String xmlResponse_JOO = readApiResponse(connection_JOO);
                // Parse the XML response to obtain user data
                parseAllUsersResponse(xmlResponse_JOO, allUsers_JOO);
            } else {
                allUsers_JOO.add("request_failed");
            }
        } catch (IOException e) {
            e.printStackTrace();
            allUsers_JOO.add("connection_error");
        }
        return allUsers_JOO;
    }

    // Helper method to validate a user's credentials
    private static String getValidateUser(String user_JOO, String password_JOO) {
        String apiUrl_JOO = protocol_JOO + serverIP_JOO + userValidationEndPoint_JOO;

        try {
            HttpURLConnection connection_JOO = openConnection(apiUrl_JOO);
            connection_JOO.setConnectTimeout(2000);
            sendFormUserData(connection_JOO, user_JOO, password_JOO);

            int responseCode_JOO = connection_JOO.getResponseCode();
            if (responseCode_JOO == 200) {
                String xmlResponse_JOO = readApiResponse(connection_JOO);
                return parseUserValidationResponse(xmlResponse_JOO);
            } else {
                return "request_failed";
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "connection_error";
        }
    }

    // Helper method to open an HTTP connection to the server
    private static HttpURLConnection openConnection(String apiUrl_JOO) throws IOException {
        URL url_JOO = new URL(apiUrl_JOO);
        HttpURLConnection connection_JOO = (HttpURLConnection) url_JOO.openConnection();
        connection_JOO.setRequestMethod("POST");
        connection_JOO.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        connection_JOO.setDoOutput(true);
        return connection_JOO;
    }

    // Helper method to send user data to the server
    private static void sendFormUserData(HttpURLConnection connection_JOO, String user_JOO, String password_JOO) throws IOException {
        try (OutputStream os_JOO = connection_JOO.getOutputStream()) {
            String data_JOO = "usuario=" + URLEncoder.encode(user_JOO, "UTF-8") + "&contrasena=" + URLEncoder.encode(password_JOO, "UTF-8");
            byte[] input_JOO = data_JOO.getBytes(StandardCharsets.UTF_8);
            os_JOO.write(input_JOO, 0, input_JOO.length);
        }
    }

    // Helper method to read the response from the server
    private static String readApiResponse(HttpURLConnection connection_JOO) throws IOException {
        try (BufferedReader reader_JOO = new BufferedReader(new InputStreamReader(connection_JOO.getInputStream(), StandardCharsets.UTF_8))) {
            StringBuilder response_JOO = new StringBuilder();
            String line_JOO;
            while ((line_JOO = reader_JOO.readLine()) != null) {
                response_JOO.append(line_JOO);
            }
            return response_JOO.toString();
        }
    }

    // Helper method to parse the response for user validation
    private static String parseUserValidationResponse(String xmlResponse_JOO) {
        try {
            Document document_JOO = parseXmlDocument(xmlResponse_JOO);
            XPath xpath_JOO = createXPath();

            XPathExpression expr_JOO = xpath_JOO.compile("//estado");
            String estado_JOO = (String) expr_JOO.evaluate(document_JOO, XPathConstants.STRING);

            return estado_JOO;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "parsing_error";
    }

    // Helper method to parse the response for all users
    private static ArrayList<String> parseAllUsersResponse(String xmlResponse_JOO, ArrayList<String> allUsers_JOO) {
        try {
            Document document_JOO = parseXmlDocument(xmlResponse_JOO);
            XPath xpath_JOO = createXPath();

            XPathExpression expr_JOO = xpath_JOO.compile("//usuario");
            NodeList userNodes_JOO = (NodeList) expr_JOO.evaluate(document_JOO, XPathConstants.NODESET);

            for (int i = 0; i < userNodes_JOO.getLength(); i++) {
                Node userNode_JOO = userNodes_JOO.item(i);
                String username_JOO = xpath_JOO.evaluate("nombre", userNode_JOO);
                String password_JOO = xpath_JOO.evaluate("contrasena", userNode_JOO);

                allUsers_JOO.add(username_JOO+";"+password_JOO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            allUsers_JOO.add("parsing_error");
        }
        return allUsers_JOO;
    }

    // Helper method to parse an XML document
    private static Document parseXmlDocument(String xml_JOO) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        return builder.parse(new InputSource(new StringReader(xml_JOO)));
    }

    // Helper method to create an XPath instance
    private static XPath createXPath() {
        XPathFactory xPathfactory = XPathFactory.newInstance();
        return xPathfactory.newXPath();
    }
}
