//import com.google.cloud.vertexai.VertexAI;
//import com.google.cloud.vertexai.api.GenerateContentResponse;
//import com.google.cloud.vertexai.generativemodels.Content;
//import com.google.cloud.vertexai.generativemodels.ChatSession;
//import com.google.cloud.vertexai.generativemodels.GenerativeModel;
//import com.google.cloud.vertexai.generativemodels.ResponseHandler;
//import java.io.IOException;
//import java.util.Scanner;
//
///*
// * To run this Java code, you need to set up your project with the Vertex AI SDK.
// * 1. Add the following Maven dependency to your project's pom.xml:
// *
// * <dependency>
// * <groupId>com.google.cloud</groupId>
// * <artifactId>google-cloud-vertexai</artifactId>
// * <version>1.0.0</version>
// * </dependency>
// *
// * 2. Authenticate with Google Cloud. For local development, the easiest way is to use the gcloud CLI:
// * a) Install gcloud: https://cloud.google.com/sdk/docs/install
// * b) Run: `gcloud auth application-default login`
// *
// * This will handle authentication automatically when you run the code.
// */
//public class GeminiChatbot {
//
//    public static void main(String[] args) {
//        // --- IMPORTANT: Replace with your project details ---
//        // Your Google Cloud project ID.
//        String projectId = "your-gcp-project-id";
//        // The location of your project (e.g., "us-central1").
//        String location = "us-central1";
//        // The model name to use.
//        String modelName = "gemini-2.0-flash";
//
//        System.out.println("Starting Gemini Chatbot...");
//
//        try {
//            runChat(projectId, location, modelName);
//        } catch (IOException e) {
//            System.err.println("An I/O error occurred: " + e.getMessage());
//            e.printStackTrace();
//        } catch (Exception e) {
//            System.err.println("An unexpected error occurred: " + e.getMessage());
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * Runs an interactive chat session with the Gemini model.
//     * @param projectId Your Google Cloud project ID.
//     * @param location The project location.
//     * @param modelName The name of the Gemini model.
//     * @throws IOException If there's an issue with I/O or the VertexAI client.
//     */
//    public static void runChat(String projectId, String location, String modelName) throws IOException {
//        // Initialize the Vertex AI client. This will use your application-default credentials.
//        try (VertexAI vertexAi = new VertexAI(projectId, location)) {
//            GenerativeModel model = new GenerativeModel(modelName, vertexAi);
//
//            // Create a new chat session to maintain conversation history
//            ChatSession chat = new ChatSession(model);
//
//            System.out.println("Chatbot is ready. Type your message or 'quit' to exit.");
//            System.out.println("----------------------------------------------------");
//
//            Scanner scanner = new Scanner(System.in);
//
//            while (true) {
//                System.out.print("You: ");
//                String userInput = scanner.nextLine();
//
//                if (userInput.equalsIgnoreCase("quit")) {
//                    System.out.println("Bot: Goodbye!");
//                    break;
//                }
//
//                try {
//                    // Send the user's message to the model
//                    GenerateContentResponse response = chat.sendMessage(userInput);
//
//                    // Get the text from the response
//                    String botResponse = ResponseHandler.getText(response);
//                    System.out.println("Bot: " + botResponse);
//
//                } catch (Exception e) {
//                    System.err.println("Error while sending message: " + e.getMessage());
//                }
//            }
//            scanner.close();
//        }
//    }
//}
