//import com.amazonaws.ClientConfiguration;
//import com.amazonaws.Protocol;
//import com.amazonaws.auth.*;
//import com.amazonaws.regions.Region;
//import com.amazonaws.regions.Regions;
//import com.amazonaws.services.s3.AmazonS3;
//import com.amazonaws.services.s3.AmazonS3Client;
//import com.amazonaws.services.s3.AmazonS3ClientBuilder;
//import com.amazonaws.services.s3.model.CannedAccessControlList;
//import com.amazonaws.services.s3.model.ObjectMetadata;
//
//import java.io.ByteArrayInputStream;
//import java.io.InputStream;
//
//public class AmazonS3FileUpload {
//
//   public void fileUpload(String base64,String fileName){
//       try{
//           AWSCredentials credentials = new BasicSessionCredentials(
//                   "ASIAUSHFMIMNB6DKCSW7",
//                   "I+XVLGLyoIDkMjvqglds588lvjEFhPcdiZ0GrkOc",
//                   "FwoGZXIvYXdzEPb//////////wEaDFxd+bngweoGn84pgSLEAeZa0JbUhiUf2Pj3rpwv2TSS6cnhrk1ahDm/NS01fBdFxpUxD+NGBLXKQicr3JVSGHSE3NsmaavqE0ctPP/N2I5UC/3utKFBxH97aTIuUM6VNzXEdnXnKVd/SCiadDNHfL61EgkQBTCOrpIf3TZU7ljyesXnGEnr1Hv0Kl1E6nQGD/Crz5qQxaune0kab9kk6lW6aTaL+txaungsee6OVjnqvvtxy2VMpPfgRdVeR3D2njMJBGIsl/8+afjYJ6hxyROdCwkohMCX+gUyLdhdfI8dlwjDY/IKCwm4pn6FMq9jw8K19IXzLgEjBxw+JSounb1WKGl1s5CLMw=="
//           ) {
//               @Override
//               public String getSessionToken() {
//                   return null;
//               }
//
//               @Override
//               public String getAWSAccessKeyId() {
//                   return null;
//               }
//
//               @Override
//               public String getAWSSecretKey() {
//                   return null;
//               }
//           };
//
//           ClientConfiguration config = new ClientConfiguration();
//           config.setProtocol(Protocol.HTTPS);
//
//           AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
//                   .withClientConfiguration(config)
//                   .withRegion(Regions.US_WEST_1)
//                   .withCredentials(new AWSStaticCredentialsProvider(credentials))
//                   .build();
//
//
//
//           byte[] bI = java.util.Base64.getDecoder().decode(base64.substring(base64.indexOf(",")+1));
//           InputStream fis = new ByteArrayInputStream(bI);
//           ObjectMetadata metadata = new ObjectMetadata();
//           metadata.setContentLength(bI.length);
//           metadata.setContentType("image/png");
//           metadata.setCacheControl("public, max-age=31536000");
//
//           s3Client.putObject("rakshith92bucket", fileName, fis, metadata);
//
//           s3Client.setObjectAcl("rakshith92bucket", fileName, CannedAccessControlList.PublicRead);
//       }catch (Exception e){
//           e.printStackTrace();
//       }
//
//
//
//       //s3client.setObjectAcl("rakshith92bucket", fileName, CannedAccessControlList.PublicRead);
//   }
//
//}
