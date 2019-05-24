package com.hisun.SO;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCMSG_ERROR_MSG;
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

public class SOZCAPP {
    boolean pgmRtn = false;
    Object CWA_PTR;
    String WK_TEMP_CODE = " ";
    short WK_CNT = 0;
    String WK_APT_CHAR = " ";
    short WK_APT = 0;
    int RESP_CODE = 0;
    int WS_COMM_LEN = 0;
    String WS_MSGID = " ";
    SOZCAPP_PGM_DOWN_NAME PGM_DOWN_NAME = new SOZCAPP_PGM_DOWN_NAME();
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
    SORSYS SORSYS = new SORSYS();
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
        CEP.TRC(SCCGWA, "SOZCAPP return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGSCA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCRCWA = (SCRCWAT) SCCGSCA_SC_AREA.CWA_AREA_PTR;
        SOZCAPP_WL1 = SCCGSCA_SC_AREA.INP_OUTP_AREA.INP_AREA_PTR;
        IBS.init(SCCGWA, SOCCPSW);
        SOCCPSW.TL_ID = WK_TELLER_NO;
        SOCCPSW.PSW = WK_PASSWORD;
        SOZCPSW SOZCPSW = new SOZCPSW();
        SOZCPSW.MP(SCCGWA, SOCCPSW);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        T000_READ_SCTCWA();
        if (pgmRtn) return;
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
