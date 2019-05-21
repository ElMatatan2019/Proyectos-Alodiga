package com.alodiga.cdr.mediar;

import java.io.*;
import java.io.File;
import java.sql.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class ReadFiles {
    
 
	
	
	public void readFolderFiles(String folder) throws ClassNotFoundException, SQLException {
    	String directory = "";
		FileReader fr = null;
		BufferedReader br = null;
		Calendar cal = GregorianCalendar.getInstance();
		Connection conn = ConexionBD.ConnecBd(Constants.urlConexion, Constants.user, Constants.password);
		try {
		PreparedStatement ps = conn.prepareStatement("");
	        File folderFile = new File(folder);
                File[] files = folderFile.listFiles();
			    for (File file : files) {
				System.out.println(file.getName());
				if(file != null){
					fr = new FileReader(file.getAbsoluteFile());
					br = new BufferedReader(fr);
					String line = "";
					int i=0;
					while((line=br.readLine()) != null){
						i++;
						Integer inte= new Integer(0);
						String[] tokens = line.split(";");

						if(tokens.length == 86){
						}
						cal.set(Calendar.YEAR, inte.parseInt(tokens[0].substring(0,4)));
						cal.set(Calendar.MONTH, (inte.parseInt(tokens[0].substring(5,7)))-1);
						cal.set(Calendar.DAY_OF_MONTH, inte.parseInt(tokens[0].substring(8,10)));
						cal.set(Calendar.HOUR_OF_DAY, inte.parseInt(tokens[0].substring(11,13)));
						cal.set(Calendar.MINUTE, inte.parseInt(tokens[0].substring(14,16)));
						cal.set(Calendar.SECOND, inte.parseInt(tokens[0].substring(17,19)));
						
						int hh = inte.parseInt(tokens[2].substring(0,3).trim());
						int mm = inte.parseInt(tokens[2].substring(4,6));
						int ss = inte.parseInt(tokens[2].substring(7,9));
						long duracion = (long) ((hh*3600) + (mm*60) + ss);
						hh = inte.parseInt(tokens[24].substring(0,3).trim());
						mm = inte.parseInt(tokens[24].substring(4,6));
						ss = inte.parseInt(tokens[24].substring(7,9));
						long h323_dest_ras_error = 0;
						if(i==0) {
							continue;
						}
						try {
							h323_dest_ras_error = tokens[42].length() > 0 ? Long.parseLong(tokens[42]) : 0; //h323_dest_ras_error
						} catch (Exception e) {
							//System.out.println("iteracion="+i);
						}
						//System.out.println("iteracion="+i);
						long value = tokens[29].length() > 0 ? Long.parseLong(tokens[29]) : 0;
						long callErrorDestCode =tokens[31].length() > 0 ? Long.parseLong(tokens[31]) : 0;
						long h323Desth225Error = tokens[43].length() > 0 ? Long.parseLong(tokens[43]) : 0;
						long sipDestRespCode = tokens[44].length() > 0 ? Long.parseLong(tokens[44]) : 0;
						long calledPartyFromSrcNumType = tokens[51].length() > 0 ? Long.parseLong(tokens[51]) : 0;
						//
						long callingPartyFromSrcNumType = tokens[58].length() > 0 ? Long.parseLong(tokens[58]) : 0; // calling_party_from_src_num_type
						long originalIsdnCauseCode =  tokens[59].length() > 0 ? Long.parseLong(tokens[59]) : 0; // original_isdn_cause_code
						long packetsRecivedOnSrcLeg = tokens[60].length() > 0 ? Long.parseLong(tokens[60]) : 0; // packets_received_on_src_leg
						long packetsLostOnSrcLeg = tokens[61].length() > 0 ? Long.parseLong(tokens[61]) : 0; // packets_lost_on_src_leg
						long packetsDiscardedOnSrcLeg = tokens[62].length() > 0 ? Long.parseLong(tokens[62]) : 0; // packets_discarded_on_src_leg
						long pdvOnSrcLeg = tokens[63].length() > 0 ? Long.parseLong(tokens[63]) : 0; // pdv_on_src_leg
						//
						long latencyOnSrcLeg = tokens[65].length() > 0 ? Long.parseLong(tokens[65]) : 0; // latency_on_src_leg
						long rfactorOnSrcLeg = tokens[66].length() > 0 ? Long.parseLong(tokens[66]) : 0; // rfactor_on_src_leg
						long packetsReceivedOnDestLeg = tokens[67].length() > 0 ? Long.parseLong(tokens[67]) : 0; // packets_received_on_dest_leg
						long packetsLostOnDestLeg = tokens[68].length() > 0 ? Long.parseLong(tokens[68]) : 0; // packets_lost_on_dest_leg
						long packetsDiscardedOnDestLeg = tokens[69].length() > 0 ? Long.parseLong(tokens[69]) : 0; // packets_discarded_on_dest_leg
						long pdvOnDestLeg = tokens[70].length() > 0 ? Long.parseLong(tokens[70]) : 0; // pdv_on_dest_leg
						//
						long latencyOnDestLeg = tokens[72].length() > 0 ? Long.parseLong(tokens[72]) : 0;
						long rfactorOnDestLeg = tokens[73].length() > 0 ? Long.parseLong(tokens[73]) : 0;
						long sipSrcRespCode = tokens[74].length() > 0 ? Long.parseLong(tokens[74]) : 0;
						long huntAttemptsIncludingLcfTries = tokens[84].length() > 0 ? Long.parseLong(tokens[84]) : 0;
						//
						Calendar calen = Calendar.getInstance();
						calen.setTimeInMillis(cal.getTime().getTime());
						if(calen.get(Calendar.SECOND )< 30){
							calen.set(Calendar.SECOND, 0);
						}else{
							calen.set(Calendar.SECOND, 0);
							calen.add(Calendar.MINUTE,1);
						}
						String sql = "INSERT INTO cdr_a_mediar VALUES  (null,'"+
						        new Timestamp(calen.getTime().getTime())+"','"+ //(1)start_time_date 
					        	Long.parseLong(tokens[1])+"','"+//(2)start_time_long
					        	duracion+"','"+//(3)call_duration_1
								tokens[3]+"','"+//(4)call_source A.B.C.D
								Long.parseLong(tokens[4])+"','"+//call_source_q931sig_port
								tokens[5]+"','"+//call_dest A.B.C.D
								tokens[7]+"','"+//call_source_custid
								tokens[8]+"','"+//called_party_on_dest
								tokens[9]+"','"	+//called_party_from_src
								tokens[10]+"','"+//call_type
								// El tokens[11] no se usa
								tokens[12]+"','"+//disconnect_error_type
								tokens[13]+"','"+//call_error_code
								tokens[14]+"','"+//call_error_description
								// El tokens[15] no se usa
								// El tokens[16] no se usa
								tokens[17]+"','"+// ani
								// El tokens[18] no se usa
								// El tokens[19] no se usa
								// El tokens[20] no se usa
								tokens[21]+"','"+//cdr_seq_no
								// El tokens[22] no se usa
								tokens[23]+"','"+// callid
								duracion+"','"+
								tokens[25]+"','"+
								tokens[26]+"','"+// call_source_uport
								tokens[27]+"','"+// call_dest_regid
								tokens[28]+"','"+// call_dest_uport
								value+"','"+ // isdn_cause_code
								tokens[30]+"','"+//called_party_after_src_calling_plan
								callErrorDestCode+"','"+//call_error_dest_code
								tokens[32]+"','"+//call_error_dest_text
								tokens[33]+"','"+//call_error_event_str
								tokens[34]+"','"+// new_ani
								Long.parseLong(tokens[35])+"','"+// call_duration_2
								tokens[36]+"','"+// incoming_leg_callid
								tokens[37]+"','"+// protocol
								tokens[38]+"','"+// cdr_type
								Long.parseLong(tokens[39])+"','"+//hunting_attempts
								tokens[40]+"','"+//caller_trunk_group
								Long.parseLong(tokens[41])+"','"+// call_pdd
								h323_dest_ras_error+"','"+//h323_dest_ras_error
								h323Desth225Error+"','"+//h323_dest_h225_error
								sipDestRespCode+"','"+// sip_dest_respcode
								tokens[45]+"','"+ // dest_trunk_group
								Double.parseDouble(tokens[46])+"','"+//call_duration_fractional
								tokens[47]+"','"+//timezone
								tokens[48]+"','"+// msw_name
								tokens[49]+"','"+// called_party_after_transit_route
								Long.parseLong(tokens[50])+"','"+//called_party_on_dest_num_type
								calledPartyFromSrcNumType+"','"+//called_party_from_src_num_type
								tokens[52]+"','"+//call_source_realm_name
								tokens[53]+"','"+//call_dest_realm_name
								tokens[54]+"','"+//call_dest_crname
								tokens[55]+"','"+// call_dest_custid
								tokens[56]+"','"+ // call_zone_data
								Long.parseLong(tokens[57])+"','"+// calling_party_on_dest_num_type
								callingPartyFromSrcNumType+"','"+// calling_party_from_src_num_type
								originalIsdnCauseCode+"','"+// original_isdn_cause_code
								packetsRecivedOnSrcLeg+"','"+// packets_received_on_src_leg
								packetsLostOnSrcLeg+"','"+// packets_lost_on_src_leg
								packetsDiscardedOnSrcLeg+"','"+// packets_discarded_on_src_leg
								pdvOnSrcLeg+"','"+// pdv_on_src_leg
								tokens[64]+"','"+// codec_on_src_leg
								latencyOnSrcLeg+"','"+// latency_on_src_leg
								rfactorOnSrcLeg+"','"+// rfactor_on_src_leg
								packetsReceivedOnDestLeg+"','"+// packets_received_on_dest_leg
								packetsLostOnDestLeg+"','"+//packets_lost_on_dest_leg
								packetsDiscardedOnDestLeg+"','"+//packets_discarded_on_dest_leg
								pdvOnDestLeg+"','"+//pdv_on_dest_leg
								tokens[71]+"','"+//codec_on_dest_leg
								latencyOnDestLeg+"','"+// latency_on_dest_leg
								rfactorOnDestLeg+"','"+// rfactor_on_dest_leg
								sipSrcRespCode+"','"+// sip_src_respcode
								tokens[75]+"','"+// peer_protocol
								tokens[76]+"','"+// src_private_ip
								tokens[77]+"','"+// dest_private_ip
								tokens[78]+"','"+// src_igrp_name
								tokens[79]+"','"+// dest_igrp_name
								tokens[80]+"','"+// diversion_info
								tokens[81]+"','"+// custom_contact_tag
								tokens[82]+"','"+// e911_call
								tokens[83]+"','"+// call_release_source
								huntAttemptsIncludingLcfTries+"','"+// hunt_attempts_including_LCF_tries
								tokens[85]+"','"+// call_gapping_error
								12+"','"
								+12+// numero del nextone en tabla switch
								"',null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null)";
						  
						try {
					
				
							ps.executeUpdate(sql);
						} catch (Exception e) {
							e.printStackTrace();
							
						}
					 sql=null;
					 line=null;
					 inte=null;
					}
				}
				Runtime garbage = Runtime.getRuntime();
				garbage.gc();	
				file =null;
				fr = null;
				br = null;
	

			}
		} catch (Exception e) {
			
			e.printStackTrace();
		} finally{
			try {
				if(conn != null){
					conn.close();
				}
				if(br != null){
					br.close();
				}
				if(fr != null){
					fr.close();
				}
			} catch (Exception e) {

				e.printStackTrace();
			}
		}
     
    }
	
    
    /**
     * Main method for the tests for the created method
     * @param args
     */
    public static void main(String args[]) {
        
    	ReadFiles rf = new ReadFiles();
        try {
			rf.readFolderFiles("/media/josemata/1d4a1314-b762-4eec-a475-64439f643463/home/josemata/Escritorio/parte3");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
    }
}