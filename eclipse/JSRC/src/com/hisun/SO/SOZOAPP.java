package com.hisun.SO;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCMSG_ERROR_MSG;
import com.hisun.BP.BPCOQCAL;
import com.hisun.BP.BPRAPT;
import com.hisun.BP.BPRCAL;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCCKDT;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.SC.SCRCWAT;
import com.hisun.SC.SCRJRN;
import com.hisun.SC.SCRPARM;

public class SOZOAPP {
    boolean pgmRtn = false;
    String PGM_BPZPQCAL = "BPZPQCAL";
    Object CWA_PTR;
    short WK_MM = 0;
    short WK_NN = 0;
    String WK_TEMP_CODE = " ";
    short WK_CNT = 0;
    char WK_HW_FLAG = ' ';
    String WK_APT_CHAR = " ";
    short WK_APT = 0;
    int RESP_CODE = 0;
    String WS_MSGID = " ";
    SOZOAPP_PGM_STAR_NAME PGM_STAR_NAME = new SOZOAPP_PGM_STAR_NAME();
    SOZOAPP_PGM_REST_NAME PGM_REST_NAME = new SOZOAPP_PGM_REST_NAME();
    String WK_PGM_NAME = "        ";
    int WK_COMM_LEN = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SOCMSG SOCMSG = new SOCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCCKDT SCCCKDT = new SCCCKDT();
    SOCCPSW SOCCPSW = new SOCCPSW();
    SCRCWA SCRCWAT = new SCRCWA();
    SCRCWAT SCRPCWA = new SCRCWAT();
    SCRJRN SCRJRN = new SCRJRN();
    BPRAPT BPRAPT = new BPRAPT();
    BPRCAL BPRCAL = new BPRCAL();
    SCRPARM SCRPARM = new SCRPARM();
    BPCOQCAL BPCOQCAL = new BPCOQCAL();
    SCCGWA SCCGWA;
    SCRCWAT SCRCWA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    String WK_APID = " ";
    String WK_TELLER_NO = " ";
    String WK_PASSWORD = " ";
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        if (pgmRtn) return;
        B000_MAIN_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "SOZOAPP return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGSCA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCRCWA = (SCRCWAT) SCCGSCA_SC_AREA.CWA_AREA_PTR;
        SOZOAPP_WL2 = SCCGSCA_SC_AREA.INP_OUTP_AREA.INP_AREA_PTR;
        IBS.init(SCCGWA, SOCCPSW);
        SOCCPSW.TL_ID = WK_TELLER_NO;
        SOCCPSW.PSW = WK_PASSWORD;
        SOZCPSW SOZCPSW = new SOZCPSW();
        SOZCPSW.MP(SCCGWA, SOCCPSW);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        T000_READ_SCTCWA();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.TR_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).trim().length() == 0) WK_MM = 0;
        else WK_MM = Short.parseShort(JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1));
        JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.TR_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1).trim().length() == 0) WK_NN = 0;
        else WK_NN = Short.parseShort(JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1));
        if (IBS.isNumeric(WK_APID)) {
            WS_MSGID = SOCMSG.SO_ERR_AP_NOT_INPUT;
            S000_ERROR_PROCESS();
            if (pgmRtn) return;
        }
        SCRPARM.KEY.TYPE = "APT";
        T000_STARTBR_SCTPARM_2();
        if (pgmRtn) return;
        T000_READNEXT_SCTPARM_2();
        if (pgmRtn) return;
        IBS.CPY2CLS(SCCGWA, SCRPARM.BLOB_VAL, BPRAPT.DATA_TXT);
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            if (SCRPARM.KEY.CODE == null) SCRPARM.KEY.CODE = "";
            JIBS_tmp_int = SCRPARM.KEY.CODE.length();
            for (int i=0;i<40-JIBS_tmp_int;i++) SCRPARM.KEY.CODE += " ";
            WK_APT_CHAR = SCRPARM.KEY.CODE.substring(0, 3);
            if (WK_APID.equalsIgnoreCase("**")) {
                B100_START_AP_MODE();
                if (pgmRtn) return;
            } else {
                if (BPRAPT.DATA_TXT.AP_MMO.equalsIgnoreCase(WK_APID)) {
