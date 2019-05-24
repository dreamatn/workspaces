package com.hisun.SO;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCMSG_ERROR_MSG;
import com.hisun.BP.BPCOCLWD;
import com.hisun.BP.BPCOQCAL;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCCWA;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.SC.SCRCWAT;
import com.hisun.SC.SCRJSCH;

public class SOZCUTC {
    boolean pgmRtn = false;
    String PGM_SOZSMGJ = "SOZSMGJ";
    Object CWA_PTR;
    String WK_PROC_NAME = "SCPJTAP";
    String WK_PARM = "P=SCPWAT92";
    String WS_BEFORE_PROC = "SCPWAT82";
    int RESP_CODE = 0;
    String WS_MSGID = " ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SOCMSG SOCMSG = new SOCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SOCCPSW SOCCPSW = new SOCCPSW();
    SCRCWA SCRCWAT = new SCRCWA();
    SCRCWAT SCRPCWA = new SCRCWAT();
    SCRJSCH SCRJSCH = new SCRJSCH();
    BPCOQCAL BPCOQCAL = new BPCOQCAL();
    BPCOCLWD BPCOCLWD = new BPCOCLWD();
    SOCSMGJ SOCSMGJ = new SOCSMGJ();
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
        CEP.TRC(SCCGWA, "SOZCUTC return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        CWA_PTR = SCCCWA;
        SCRCWA = (SCRCWAT) CWA_PTR;
        SCCGSCA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SOZCUTC_WL2 = SCCGSCA_SC_AREA.INP_OUTP_AREA.INP_AREA_PTR;
        IBS.init(SCCGWA, SOCCPSW);
        SOCCPSW.TL_ID = WK_TELLER_NO;
        SOCCPSW.PSW = WK_PASSWORD;
        SOZCPSW SOZCPSW = new SOZCPSW();
        SOZCPSW.MP(SCCGWA, SOCCPSW);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        if (SCRCWA.SYS_STUS != 'A') {
            WS_MSGID = SOCMSG.SYS_CLS;
            S000_ERROR_PROCESS();
            if (pgmRtn) return;
        }
        if (SCRCWA.BUSS_MODE == 'O') {
            WS_MSGID = SOCMSG.SO_ERR_BUSS_OPEN;
            S000_ERROR_PROCESS();
            if (pgmRtn) return;
        }
        SCRJSCH.KEY.JOB_NAME = WS_BEFORE_PROC;
        T000_READ_SCTJSCH();
        if (pgmRtn) return;
        if (SCRJSCH.JOB_STS != 'F') {
            WS_MSGID = SOCMSG.SO_ERR_WAIT_JOB;
            S000_ERROR_PROCESS();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, SOCSMGJ);
        SOCSMGJ.PROC_NAME = WK_PROC_NAME;
        SOCSMGJ.PROC_DATA = WK_PARM;
        SOZSMGJ SOZSMGJ = new SOZSMGJ();
        SOZSMGJ.MP(SCCGWA, SOCSMGJ);
        if (SOCSMGJ.RC_CODE != 0) {
            WS_MSGID = SOCMSG.SO_ERR_SEND_JOB;
            S000_ERROR_PROCESS();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPCOQCAL);
        BPCOQCAL.FUNC = '8';
        BPCOQCAL.BK = SCCGWA.COMM_AREA.TR_BANK;
        S000_CALL_BPZPQCAL();
        if (pgmRtn) return;
        BPCOQCAL.CAL_CODE = "11231313";
        IBS.init(SCCGWA, BPCOCLWD);
        BPCOCLWD.CAL_CODE = BPCOQCAL.CAL_CODE;
        BPCOCLWD.DAYE_FLG = 'N';
        BPCOCLWD.DATE1 = SCRCWA.AC_DATE_AREA[3-1].AC_DATE;
        BPCOCLWD.WDAYS = 2;
        S000_CALL_BPZPCLWD();
        if (pgmRtn) return;
        SCRCWA.AC_DATE_AREA[3-1].LAST_AC_DATE = SCRCWA.AC_DATE_AREA[3-1].AC_DATE;
        SCRCWA.AC_DATE_AREA[3-1].AC_DATE = BPCOCLWD.DATE2;
        IBS.init(SCCGWA, BPCOCLWD);
        BPCOCLWD.CAL_CODE = BPCOQCAL.CAL_CODE;
        BPCOCLWD.DAYE_FLG = 'N';
        BPCOCLWD.DATE1 = SCRCWA.AC_DATE_AREA[3-1].AC_DATE;
        BPCOCLWD.WDAYS = 2;
        S000_CALL_BPZPCLWD();
        if (pgmRtn) return;
        SCRCWA.AC_DATE_AREA[3-1].NEXT_AC_DATE = BPCOCLWD.DATE2;
        if (SCRCWA.JRN_IN_USE == '1') {
            SCRCWA.JRN_IN_USE = '2';
        } else {
            SCRCWA.JRN_IN_USE = '1';
        }
        T000_READ_UPD_SCTCWA();
        if (pgmRtn) return;
        SCRPCWA.AC_DATE_AREA[3-1].AC_DATE = SCRCWA.AC_DATE_AREA[3-1].AC_DATE;
        SCRPCWA.AC_DATE_AREA[3-1].LAST_AC_DATE = SCRCWA.AC_DATE_AREA[3-1].LAST_AC_DATE;
        SCRPCWA.AC_DATE_AREA[3-1].NEXT_AC_DATE = SCRCWA.AC_DATE_AREA[3-1].NEXT_AC_DATE;
        SCRPCWA.JRN_IN_USE = SCRCWA.JRN_IN_USE;
        T000_REWRITE_SCTCWA();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZPQCAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-CAL-CODE", BPCOQCAL);
        CEP.TRC(SCCGWA, BPCOQCAL.RC.RC_CODE);
        if (BPCOQCAL.RC.RC_CODE != 0) {
            WS_MSGID = SOCMSG.SO_ERR_CAL_CODE;
            S000_ERROR_PROCESS();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPCLWD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-CAL-WORK-DAY", BPCOCLWD);
        if (BPCOCLWD.RC.RC_CODE != 0) {
            WS_MSGID = SOCMSG.SO_ERR_CAL_WORK;
            S000_ERROR_PROCESS();
            if (pgmRtn) return;
        }
    }
    public void S000_ERROR_PROCESS() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSGID);
        Z_RET();
        if (pgmRtn) return;
    }
    public void S000_SEND_MSG_TO_OP() throws IOException,SQLException,Exception {
    }
    public void T000_READ_SCTJSCH() throws IOException,SQLException,Exception {
        SCTJSCH_RD = new DBParm();
        SCTJSCH_RD.TableName = "SCTJSCH";
        IBS.READ(SCCGWA, SCRJSCH, SCTJSCH_RD);
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
