package com.hisun.SO;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCMSG_ERROR_MSG;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.SC.SCRCWAT;

public class SOZCONL {
    boolean pgmRtn = false;
    String PGM_BPZMMTL = "BPZMMTL ";
    Object CWA_PTR;
    int RESP_CODE = 0;
    String WS_MSGID = " ";
    long WS_NEXT_JRN_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SOCMSG SOCMSG = new SOCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SOCCPSW SOCCPSW = new SOCCPSW();
    SCRCWA SCRCWAT = new SCRCWA();
    SCRCWAT SCRPCWA = new SCRCWAT();
    SCCGWA SCCGWA;
    SCRCWAT SCRCWA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    String WK_TELLER_NO = " ";
    String WK_PASSWORD = " ";
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        if (pgmRtn) return;
        B000_MAIN_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "SOZCONL return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGSCA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SOZCONL_WL2 = SCCGSCA_SC_AREA.INP_OUTP_AREA.INP_AREA_PTR;
        IBS.init(SCCGWA, SOCCPSW);
        SOCCPSW.TL_ID = WK_TELLER_NO;
        SOCCPSW.PSW = WK_PASSWORD;
        SOZCPSW SOZCPSW = new SOZCPSW();
        SOZCPSW.MP(SCCGWA, SOCCPSW);
        WK_PASSWORD = " PSWD ";
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        T000_READ_UPD_SCTCWA();
        if (pgmRtn) return;
        if (SCRPCWA.SYS_STUS != 'A') {
            WS_MSGID = SOCMSG.SYS_CLS;
            S000_ERROR_PROCESS();
            if (pgmRtn) return;
        }
        SCRPCWA.NEXT_JRN_NO1 = IBS.SEQUENCE(SCCGWA, "JRN_SEQ1");
        SCRPCWA.NEXT_JRN_NO2 = IBS.SEQUENCE(SCCGWA, "JRN_SEQ2");
        CEP.TRC(SCCGWA, SCRPCWA.NEXT_JRN_NO1);
        CEP.TRC(SCCGWA, SCRPCWA.NEXT_JRN_NO2);
        SCRPCWA.SYS_STUS = 'I';
        T000_REWRITE_SCTCWA();
        if (pgmRtn) return;
    }
    public void S000_ERROR_PROCESS() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSGID);
        Z_RET();
        if (pgmRtn) return;
    }
    public void S000_SEND_MSG_TO_OP() throws IOException,SQLException,Exception {
    }
    public void T000_READ_UPD_SCTCWA() throws IOException,SQLException,Exception {
        SCRCWAT.KEY.BANK_NO = SCCGWA.COMM_AREA.TR_BANK;
        SCTCWA_RD = new DBParm();
        SCTCWA_RD.TableName = "SCTCWA";
        SCTCWA_RD.upd = true;
        IBS.READ(SCCGWA, SCRCWAT, SCTCWA_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            WS_MSGID = SOCMSG.SO_SYS_ERROR;
            S000_ERROR_PROCESS();
            if (pgmRtn) return;
        }
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCRCWAT);
