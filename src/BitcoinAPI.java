/**
decodescript "hex"
dumpwallet "filename"
getaccount "bitcoinaddress"
getaccountaddress "account"
backupwallet "destination"
importwallet "filename"
validateaddress "bitcoinaddress"
settxfee amount
addnode "node" "add|remove|onetry"
createrawtransaction [{"txid":"id","vout":n},...] {"address":amount,...}
getaddednodeinfo dns ( "node" )
getbalance ( "account" minconf )
getblocktemplate ( "jsonrequestobject" )
getnetworkhashps ( blocks height )
getreceivedbyaccount "account" ( minconf )
getreceivedbyaddress "bitcoinaddress" ( minconf )
gettxout "txid" n ( includemempool )
getwork ( "data" )
help ( "command" )
importprivkey "bitcoinprivkey" ( "label" rescan )
keypoolrefill ( newsize )
listaccounts ( minconf )
listreceivedbyaccount ( minconf includeempty )
listsinceblock ( "blockhash" target-confirmations )
listtransactions ( "account" count from )
listunspent ( minconf maxconf ["address",...] )
lockunspent unlock [{"txid":"txid","vout":n},...]
move "fromaccount" "toaccount" amount ( minconf "comment" )
sendfrom "fromaccount" "tobitcoinaddress" amount ( minconf "comment" "comment-to" )
sendmany "fromaccount" {"address":amount,...} ( minconf "comment" )
sendtoaddress "bitcoinaddress" amount ( "comment" "comment-to" )
setaccount "bitcoinaddress" "account"
setgenerate generate ( genproclimit )
signmessage "bitcoinaddress" "message"
signrawtransaction "hexstring" ( [{"txid":"id","vout":n,"scriptPubKey":"hex","redeemScript":"hex"},...] ["privatekey1",...] sighashtype )
submitblock "hexdata" ( "jsonparametersobject" )
verifychain ( checklevel numblocks )
verifymessage "bitcoinaddress" "signature" "message"
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.LinkedHashMap;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

public class BitcoinAPI {

	static final Logger logger = LogManager.getLogger(BitcoinAPI.class
			.getName());

	private String rpcuser = "...";
	private String rpcpassword = "...";
	private String rpcurl = "...";
	private String rpcport = "...";
	private String walletpassphrase = "...";

	/**
	 * 
	 */
	public BitcoinAPI() {
		logger.entry();
		try {
			Properties p = new Properties();
			p.loadFromXML(new FileInputStream("bitcoinAPIsettings.xml"));
			this.rpcuser = p.getProperty("rpcuser");
			this.rpcpassword = p.getProperty("rpcpassword");
			this.rpcurl = p.getProperty("rpcurl");
			this.rpcport = p.getProperty("rpcport");
			this.walletpassphrase = p.getProperty("walletpassphrase");

			// System.out.println(getbalance());
			// System.out.println(listreceivedbyaddress());
		} catch (Exception e) {
			logger.catching(e);
		}
		logger.exit(this);
	}

	// //////////////////////////
	// /Zero Parameters Calls////
	// //////////////////////////

	/**
	 * @return
	 */
	public String getblockcount() {
		logger.entry();
		String returnAnswer = generateRequest("getblockcount");
		logger.exit(returnAnswer);
		return returnAnswer;
	}

	/**
	 * @return
	 */
	public String getdifficulty() {
		logger.entry();
		String returnAnswer = generateRequest("getdifficulty");
		logger.exit(returnAnswer);
		return returnAnswer;

	}

	/**
	 * @return
	 */
	public String getgenerate() {
		logger.entry();
		String returnAnswer = generateRequest("getgenerate");
		logger.exit(returnAnswer);
		return returnAnswer;

	}

	/**
	 * @return
	 */
	public String getinfo() {
		logger.entry();
		String returnAnswer = generateRequest("getinfo");
		logger.exit(returnAnswer);
		return returnAnswer;

	}

	/**
	 * @return
	 */
	public String getbestblockhash() {
		logger.entry();
		String returnAnswer = generateRequest("getrawchangeaddress");
		logger.exit(returnAnswer);
		return returnAnswer;

	}

	/**
	 * @return
	 */
	public String getrawchangeaddress() {
		logger.entry();
		String returnAnswer = generateRequest("getrawchangeaddress");
		logger.exit(returnAnswer);
		return returnAnswer;

	}

	/**
	 * @return
	 */
	public String getbalance() {
		logger.entry();
		String returnAnswer = generateRequest("getbalance");
		logger.exit(returnAnswer);
		return returnAnswer;

	}

	/**
	 * @return
	 */
	public String listreceivedbyaddress() {
		logger.entry();
		String returnAnswer = generateRequest("listreceivedbyaddress");
		logger.exit(returnAnswer);
		return returnAnswer;

	}

	/**
	 * @return
	 */
	public String openwallet() {
		logger.entry();
		String returnAnswer = generateRequest("walletpassphrase",
				this.walletpassphrase, 600);
		logger.exit(returnAnswer);
		return returnAnswer;

	}

	/**
	 * @return
	 */
	public String closewallet() {

		logger.entry();
		String returnAnswer = this.generateRequest("walletlock");
		logger.exit(returnAnswer);
		return returnAnswer;

	}

	/**
	 * @return
	 */
	public String getconnectioncount() {
		logger.entry();
		String returnAnswer = generateRequest("getconnectioncount");
		logger.exit(returnAnswer);
		return returnAnswer;

	}

	/**
	 * @return
	 */
	public String gethashespersec() {
		logger.entry();
		String returnAnswer = generateRequest("gethashespersec");
		logger.exit(returnAnswer);
		return returnAnswer;

	}

	/**
	 * @return
	 */
	public String getmininginfo() {
		logger.entry();
		String returnAnswer = generateRequest("getmininginfo");
		logger.exit(returnAnswer);
		return returnAnswer;

	}

	/**
	 * @return
	 */
	public String getnettotals() {
		logger.entry();
		String returnAnswer = generateRequest("getnettotals");
		logger.exit(returnAnswer);
		return returnAnswer;

	}

	/**
	 * @return
	 */
	public String getpeerinfo() {
		logger.entry();
		String returnAnswer = generateRequest("getpeerinfo");
		logger.exit(returnAnswer);
		return returnAnswer;

	}

	/**
	 * @return
	 */
	public String listaddressgroupings() {
		logger.entry();
		String returnAnswer = generateRequest("listaddressgroupings");
		logger.exit(returnAnswer);
		return returnAnswer;

	}

	/**
	 * @return
	 */
	public String listlockunspent() {
		logger.entry();
		String returnAnswer = generateRequest("listlockunspent");
		logger.exit(returnAnswer);
		return returnAnswer;

	}

	/**
	 * @return
	 */
	public String ping() {
		logger.entry();
		String returnAnswer = generateRequest("ping");
		logger.exit(returnAnswer);
		return returnAnswer;

	}

	/**
	 * @return
	 */
	public String stop() {
		logger.entry();
		String returnAnswer = generateRequest("stop");
		logger.exit(returnAnswer);
		return returnAnswer;

	}

	/**
	 * @return
	 */
	public String gettxoutsetinfo() {
		logger.entry();
		String returnAnswer = generateRequest("gettxoutsetinfo");
		logger.exit(returnAnswer);
		return returnAnswer;

	}

	/**
	 * @return
	 */
	public String getunconfirmedbalance() {
		logger.entry();
		String returnAnswer = generateRequest("getunconfirmedbalance");
		logger.exit(returnAnswer);
		return returnAnswer;

	}

	// //////////////////////////
	// /Single Parameter Calls//
	// //////////////////////////

	/**
	 * @param txid
	 * @return
	 */
	public String gettransaction(String txid) {
		logger.entry(txid);
		String returnAnswer = generateRequest("gettransaction", txid);
		logger.exit(returnAnswer);
		return returnAnswer;

	}

	/**
	 * @param verbose
	 * @return
	 */
	public String getrawmempool(boolean... verbose) {
		String returnAnswer = "";
		if (verbose.length > 0) {
			logger.entry(verbose);
			returnAnswer = generateRequest("getrawmempool", verbose[0]);
		} else {
			returnAnswer = generateRequest("getrawmempool", false);
		}
		logger.exit(returnAnswer);
		return returnAnswer;
	}

	/**
	 * @param txid
	 * @return
	 */
	public String getrawtransaction(String txid) {
		logger.entry(txid);
		String returnAnswer = generateRequest("getrawtransaction", txid);
		logger.exit(returnAnswer);
		return returnAnswer;

	}

	/**
	 * @param hexstring
	 * @return
	 */
	public String decoderawtransaction(String hexstring) {
		logger.entry(hexstring);
		String returnAnswer = generateRequest("decoderawtransaction", hexstring);
		logger.exit(returnAnswer);
		return returnAnswer;

	}

	/**
	 * @param hash
	 * @return
	 */
	public String getblock(String hash) {
		logger.entry(hash);
		String returnAnswer = generateRequest("getblock", hash);
		logger.exit(hash);
		return returnAnswer;

	}

	/**
	 * @param index
	 * @return
	 */
	public String getblockhash(int index) {
		logger.entry(index);
		String returnAnswer = this.generateRequest("getblockhash", index);
		logger.exit(returnAnswer);
		return returnAnswer;
	}

	/**
	 * @param label
	 * @return
	 */
	public String getnewaddress(String label) {
		logger.entry(label);
		String returnAnswer = generateRequest("getnewaddress", label);
		logger.exit(returnAnswer);
		return returnAnswer;

	}

	/**
	 * @param account
	 * @return
	 */
	public String getaddressesbyaccount(String account) {
		logger.entry(account);
		String returnAnswer = generateRequest("getaddressesbyaccount", account);
		logger.exit(returnAnswer);
		return returnAnswer;

	}

	/**
	 * @param address
	 * @return
	 */
	public String dumpprivkey(String address) {
		logger.entry(address);
		this.closewallet();
		this.openwallet();
		String privateKey = generateRequest("dumpprivkey", address);
		this.closewallet();
		logger.exit();
		return privateKey;
	}

	/**
	 * @param rawTransactionHex
	 * @return
	 */
	public String sendRawTransaction(String rawTransactionHex) {
		logger.entry(rawTransactionHex);
		String returnAnswer = generateRequest("sendrawtransaction",
				rawTransactionHex);
		logger.exit(returnAnswer);
		return returnAnswer;
	}

	// //////////////////////////
	// /Two Parameters Calls//
	// //////////////////////////
	
	// Done
	/**
	 * @param oldpassphrase
	 * @param newpassphrase
	 * @return
	 */
	public String walletpassphrasechange(String oldpassphrase, String newpassphrase) {
		logger.entry();
		String returnAnswer = generateRequest("walletpassphrasechange",
				oldpassphrase,newpassphrase);
		logger.exit(returnAnswer);
		return returnAnswer;
	}
	
	// //////////////////////////
	// /Unkown Parameters Calls//
	// //////////////////////////
	
	// Dosn't work for some reason
	/**
	 * @param nrequired
	 * @param key
	 * @return
	 */
	public String createmultisig(int nrequired, String... key) {
		logger.entry(nrequired);

		JSONArray address = new JSONArray();
		if (key.length > 0) {
			for (int i = 0; i < key.length; i++) {

				address.put(key[i]);
			}
		}
		return generateRequest("createmultisig", nrequired, address);

	}

	// Dosn't work for some reason
	/**
	 * @param nrequired
	 * @param label
	 * @param key
	 * @return
	 */
	public String addmultisigaddress(int nrequired, String label, String... key) {
		logger.entry(nrequired);

		JSONArray address = new JSONArray();
		if (key.length > 0) {
			for (int i = 0; i < key.length; i++) {

				address.put(key[i]);
			}
		}
		if (label.equals(""))
			return generateRequest("addmultisigaddress", nrequired, address);
		else
			return generateRequest("addmultisigaddress", nrequired, address,
					label);

	}

	// Dosn't work for some reason
	/**
	 * @param txs
	 * @param sendAddresses
	 * @return
	 */
	public String createrawtransaction(JSONArray txs, JSONObject sendAddresses) {
		logger.entry(sendAddresses.toString());
		String returnAnswer = generateRequest("createrawtransaction", txs,
				sendAddresses);
		logger.exit(returnAnswer);
		return generateRequest("createrawtransaction", txs, sendAddresses);
	}

	
	// ////////////////Sending RPC calls/////////////////////////////////

	private static final Charset QUERY_CHARSET = Charset.forName("ISO8859-1");

	@SuppressWarnings({ "rawtypes", "unchecked", "serial" })
	private byte[] prepareRequest(final String method, final Object... params) {
		return JSON.stringify(new LinkedHashMap() {
			{
				put("jsonrpc", "1.0");
				put("id", "1");
				put("method", method);
				put("params", params);
			}
		}).getBytes(QUERY_CHARSET);
	}

	private String generateRequest(String method, Object... param) {

		String requestBody = new String(this.prepareRequest(method, param));

		if (method.equals("dumpprivkey"))
			logger.entry("dumpprivkey so no logging");
		else if (method.equals("getblock"))
			logger.entry("It's to long to log an entire block");
		else if (method.equals("walletpassphrasechange"))
			logger.entry("walletpassphrasechange so no logging");
		else
			logger.entry(requestBody);

		final PasswordAuthentication temp = new PasswordAuthentication(
				this.rpcuser, this.rpcpassword.toCharArray());
		Authenticator.setDefault(new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return temp;
			}
		});
		String uri = "http://" + this.rpcurl + ":" + this.rpcport;

		String contentType = "application/json";
		HttpURLConnection connection = null;
		try {
			URL url = new URL(uri);
			connection = (HttpURLConnection) url.openConnection(Proxy.NO_PROXY);
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", contentType);
			connection.setRequestProperty("Accept", "application/json");
			connection.setRequestProperty("Content-Length",
					Integer.toString(requestBody.getBytes().length));
			connection.setUseCaches(true);
			connection.setDoInput(true);
			OutputStream out = connection.getOutputStream();
			out.write(requestBody.getBytes());
			out.flush();
			out.close();
		} catch (Exception ioE) {
			logger.catching(ioE);
			connection.disconnect();
			ioE.printStackTrace();
		}

		try {
			if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				InputStream is = connection.getInputStream();
				BufferedReader rd = new BufferedReader(
						new InputStreamReader(is));
				String line;
				StringBuffer response = new StringBuffer();
				while ((line = rd.readLine()) != null) {
					response.append(line);
					response.append('\r');
				}
				rd.close();
				// System.out.println(response);

				String responseToString = response.toString();
				try {
					JSONObject json = new JSONObject(responseToString);
					String returnAnswer = json.get("result").toString();

					if (method.equals("dumpprivkey"))
						logger.exit("dumpprivkey so no logging");
					else if (method.equals("getblock"))
						logger.exit("It's to long to log an entire block");
					else if (method.equals("walletpassphrasechange"))
						logger.exit("walletpassphrasechange so no logging");
					else
						logger.exit(returnAnswer);

					return returnAnswer;
				} catch (Exception e) {
					logger.catching(e);
					return "";
				}
			} else {
				System.out.println("Coudln't connet to Bitcoind!");
				logger.exit("Coudln't connet to Bitcoind!");
				connection.disconnect();
			}
		} catch (Exception e) {
			logger.catching(e);
		}
		logger.exit("Couldn't get a decent answer");
		return "";
	}

	// //////////////////////////////////////////////////////////////////

}
