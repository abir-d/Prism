import java.io.PrintStream;
import java.net.URLEncoder;
import java.util.Scanner;

public class GenerateSSOLinkMO
{
  static final String JDBC_DRIVER = "oracle.jdbc.OracleDriver";
  
  public static String generateLink1(String username, String customerId, String orgNodeCodePath, String orgNodeLevel, boolean admin)
  {
    String generatedUrl = "";
    String role = admin ? "Admin" : "User";
    try
    {
      System.out.println("-------------------------------------------------");
      String param = "customer_id=" + customerId + "&org_node_code=" + URLEncoder.encode(orgNodeCodePath, "UTF-8") + "&hierarchy_level=" + orgNodeLevel + "&application_name=DRC&time_stamp=2015-11-26T00%3A42%3A45Z&user_role=" + role + "&user_name=" + username;
      String signature = DigitalMeasuresHMACQueryStringBuilder.getSignature(param, "DRC");
      String perfUrl ="https://app3.ctb.com/prism-mo";
      //String perfUrl ="http://localhost:8080/prism";
      generatedUrl = perfUrl + "/reports.do?" + param + "&signature=" + signature + "&project=usmo";
      System.out.println(generatedUrl);
      System.out.println("-------------------------------------------------");
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
      System.out.println("ERROR .......!......!......... ");
    }
    return generatedUrl;
  }
  
  public static void main(String[] args)
  {
    Scanner reader = new Scanner(System.in);
    System.out.print("Provide org_node_code: ");
    String orgNodeCode = reader.nextLine();
    System.out.print("Provide hierarchy_level: ");
    String hierarchyLevel = reader.nextLine();
    String userName = "testdrc" + orgNodeCode.substring(orgNodeCode.indexOf("~") + 1) + hierarchyLevel;
    generateLink1(userName, "", orgNodeCode, hierarchyLevel, false);
  }
}
