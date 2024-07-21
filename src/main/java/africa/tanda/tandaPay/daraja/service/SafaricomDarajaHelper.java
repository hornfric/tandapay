package africa.tanda.tandaPay.daraja.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;


import org.apache.commons.codec.binary.Base64;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;


public class SafaricomDarajaHelper {
	
	
	public void generateSecurityCredential(String password) {
		String  encryptedPassword ;
		byte[] passwordStringArray=password.getBytes();
		  Resource resource = new ClassPathResource("cert.cer");
          try {
			InputStream inputStream = resource.getInputStream();
			 FileInputStream fin = new FileInputStream(resource.getFile());
	            Cipher cipher = Cipher.getInstance("AES", "BC");
	            CertificateFactory cf = CertificateFactory.getInstance("X.509");
	            X509Certificate certificate = (X509Certificate) cf.generateCertificate(fin);
	            PublicKey pk = certificate.getPublicKey();
	            cipher.init(Cipher.ENCRYPT_MODE, pk);

	            byte[] cipherText = cipher.doFinal(passwordStringArray);
	            encryptedPassword =new String( Base64.encodeBase64(cipherText));
	            System.out.println("The encypted Password id:"+encryptedPassword);
		} catch (IOException | NoSuchAlgorithmException | NoSuchProviderException | NoSuchPaddingException | CertificateException | IllegalBlockSizeException | BadPaddingException | InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
