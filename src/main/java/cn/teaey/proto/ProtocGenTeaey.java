package cn.teaey.proto;
import static com.google.protobuf.Descriptor.*;
import static com.google.protobuf.Plugin.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
/**
 * @author Teaey
 * @email masfay@163.com
 * @since 2013-05-24
 */
public final class ProtocGenTeaey
{
    private static final Logger logger = LoggerFactory.getLogger(ProtocGenTeaey.class);
    public static void main(String[] args) throws IOException
    {
        logger.info("Start Protoc Gen Teaey...");
        CodeGeneratorRequest request = CodeGeneratorRequest.
                parseFrom(System.in);
        CodeGeneratorResponse response;
        CodeGeneratorResponse.Builder responseBuilder = CodeGeneratorResponse.newBuilder();
        try
        {
            //CodeGeneratorResponse.File.Builder fb = CodeGeneratorResponse.File.newBuilder();
            new CodeGenerator.DefaultCodeGenerator().generate(request);
        } catch (Exception e)
        {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            pw.flush();
            pw.close();
            responseBuilder = CodeGeneratorResponse.newBuilder();
            responseBuilder.setError(sw.toString());
        }
        responseBuilder.build().writeTo(System.out);
        System.out.flush();
    }
}
