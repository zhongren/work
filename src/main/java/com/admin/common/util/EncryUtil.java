package com.admin.common.util;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;

/**
 * Created by ZR_a on 2017/8/25.
 */
public class EncryUtil {
    public static final String ALGORITHM_SHA_256 = "SHA-256" ;
    public static final String ALGORITHM_MD5 = "MD5" ;
    public static final String ALGORITHM_SHA_1 = "SHA1" ;
    public static final String ALGORITHM_AES = "AES" ;
    public static final int ALGORITHM_SHA_256_LENTH = 32 ;
    public static final int UL_ENCODE_NUMBER = 65535 ;
    public static SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final  String authKey = "pop_fabric_app!#^" ;
    /**
     * To do : Encrypt password with sha-256 ...
     *
     * @param password
     * @return
     */
    public static String encryPassword( String password ){
        if( password == null || password.equals( "" ) ) return null ;
        return encry2Hex( password , ALGORITHM_SHA_256 ) ;
    }


    /**
     * To do : Encrypt with md5 ...
     *
     * @param source
     * @return
     */
    public static String encryMD5( String source ){
        if( source == null || source.equals( "" ) ) return null ;
        return encry2Hex( source , ALGORITHM_MD5 ) ;
    }

    public static String dblEncryMD5( String source ){
        if( source == null || source.equals( "" ) ) return null ;
        return encryMD5( encryMD5( source ) ) ;
    }

    /**
     * To do : Encrypting ...
     *
     * @param String source
     * @return
     */
    public static byte[] encry( byte[] source , int offset , int length , String encryType ){
        MessageDigest messageDigest = null ;
        try{
            messageDigest = MessageDigest.getInstance( encryType ) ;
            messageDigest.update( source , offset , length );
        }catch( NoSuchAlgorithmException e ){
            e.printStackTrace();
        }
        return  messageDigest.digest( ) ;
    }


    /**
     * To do : Encrypt to hex ...
     *
     * @param source
     * @param encryType
     * @return
     */
    public static String encry2Hex( String source , String encryType ){
        byte [] sourceBytes = source.getBytes() ;
        return bytes2Hex( encry( sourceBytes , 0 , sourceBytes.length , encryType ) ) ;
    }
    public static String bytes2Hex(byte[] bytes ) {
        String des = "" , tmp  = null;
        for ( int i = 0; i < bytes.length ; i++ ) {
            tmp = ( Integer.toHexString( bytes[i] & 0xFF ) );
            if ( tmp.length() == 1 ) des += "0";
            des += tmp;
        }
        return des;
    }

    /**
     *
     * To do : Encrypt token ...
     *
     * @param content
     * @param offset
     * @param length
     * @param secretKey
     * @param iv
     * @return
     */
    public static byte[] encryToken(byte[] content , int offset , int length , SecretKey secretKey , byte [] iv ){
        Cipher ticketCipher = null ;
        try{
            ticketCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            ticketCipher.init( Cipher.ENCRYPT_MODE , secretKey , new IvParameterSpec( iv ) );
            return ticketCipher.doFinal( content , offset , length ) ;
        }catch(Exception e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Decryption failed");
    }

    /**
     * To do : Decrypt token ...
     *
     * @param content
     * @param offset
     * @param length
     * @param secretKey
     * @param iv
     * @return
     */
    public static byte[] decryToken( byte[] content , int offset , int length , SecretKey secretKey , byte[] iv ){
        Cipher ticketCipher = null ;
        try{
            ticketCipher = Cipher.getInstance( "AES/CBC/PKCS5Padding" ) ;
            ticketCipher.init( Cipher.DECRYPT_MODE , secretKey , new IvParameterSpec( iv ) );
            return ticketCipher.doFinal( content , offset , length ) ;
        }catch( Exception e ){
            e.printStackTrace();
        }
        throw new RuntimeException("Decrypt token error ." ) ;
    }

    /**
     * To do Encrypt base64  ...
     *
     * @param source
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String encryBASE64( byte[] source ){
        String result = null ;
        try {
            result = new String( Base64.encodeBase64( source ) , "UTF-8" ) ;

        } catch (UnsupportedEncodingException e) {
            //...
        }
        return result ;
    }

    /**
     * To do : Decrypt base64 ...
     *
     * @param source
     * @return
     */
    public static byte[] decryBASE64( String source ){

        return Base64.decodeBase64( source.getBytes() ) ;
    }

    /**
     * decrypt token ...
     *
     * @param source
     * @param key
     * @return
     * @throws NoSuchAlgorithmException
     * @throws IOException
     */
    public static String decryPHPToken( String source , String key ) throws NoSuchAlgorithmException, IOException{
        byte[] sourceBase64 = Base64.decodeBase64( source.getBytes() ) ;
        String txt = popKey( sourceBase64 , encryMD5(key) ) ;
        String rs = "" ;
        for( int i = 0 ; i < txt.length() ; i ++ ){
            char temp = txt.charAt( i ) ;
            rs += (char)(txt.charAt( ++i ) ^ temp  );
        }
        return rs ;
    }

    public static String  encryPHPToken( String source , String key ){
        if( source == null ){
            return null ;
        }
        int random = (int) Math.round( Math.random( ) * 999999 ) ;
        String mdRundom = encryMD5( String.valueOf( random ) );
        int index = 0;
        String result = "";
        for ( int i = 0; i < source.length(); i++ ) {
            if( index == mdRundom.length() ){
                index = 0;
            }
            result +=  "" + mdRundom.charAt(index )  + (char) (source.charAt(i) ^ mdRundom.charAt( index ) ) ;
            index ++  ;
        }
        return encryBASE64( popKey( result.getBytes() ,encryMD5( key )  ).getBytes()  );
    }

    private static String popKey( byte[] source , String key ){
        int crt = 0 ;
        String temp = "" ;
        for( int i = 0 ; i < source.length ; i ++ ){
            crt = crt == key.length() ? 0 : crt ;
            temp+= (char)(source[i] ^ key.charAt(crt++) );
        }
        return temp ;
    }

    public static String duplicatMD5( String source ){
        if( source == null || source.trim().equals("") ){
            return null ;
        }
        return encryMD5( encryMD5( source ) ) ;
    }

    /**
     * 简单异或加密算法
     *
     * @param info
     * @param key
     * @return
     */
    public static String xorEncryption(String info, String key) {
        if (org.apache.commons.lang3.StringUtils.isEmpty(info) ||
                org.apache.commons.lang3.StringUtils.isEmpty(key)) {
            return null;
        }
        byte[] result = new byte[info.length()];
        for (int i = 0, j = 0; i < info.length(); ++i) {
            result[i] = (byte) (info.charAt(i) ^ key.charAt(j));
            j = (++j) % (key.length());
        }
        return encryBASE64(result);
    }

    private static String strRandom( int length , int [][] range ){
        if( length <= 0 ) return "" ;
        StringBuilder random = new StringBuilder()  ;
        for( int i = 0 ; i < length ; i ++ ){
            int index = (int) Math.round( Math.random() * 2 ) ;
            int [] temp = range [index] ;
            int num = (int) Math.round( Math.random() * ( temp[1] - temp[0] ) + temp[0] ) ;
            random.append((char)num) ;
        }
        return random.toString() ;
    }

    public static String numRandom( int length ){
        int [][] range = new int[][]{{48,57}} ;
        return strRandom( length , range ) ;
    }

    public static String strRandom( int length ){
        int [][] range = new int[][]{{65,90},{97,122},{48,57}} ;
        return strRandom( length , range ) ;
    }

}
