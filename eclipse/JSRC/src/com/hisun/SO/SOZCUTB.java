package com.hisun.SO;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCMSG_ERROR_MSG;
import com.hisun.BP.BPCOCLWD;
import com.hisun.BP.BPCOQCAL;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.SC.SCRCWAT;

public class SOZCUTB {
    boolean pgmRtn = false;
    String PGM_SOZUPARM = "SOZUPARM";
    Object CWA_PTR;
    int RESP_CODE = 0;
    String WS_MSGID = " ";
    SOZCUTB_WS_CAL_DATE WS_CAL_DATE = new SOZCUTB_WS_CAL_DATE();
    SOZCUTB_WS_TMP_DATE1 WS_TMP_DATE1 = new SOZCUTB_WS_TMP_DATE1();
    SOZCUTB_WS_TMP_DATE2 WS_TMP_DATE2 = new SOZCUTB_WS_TMP_DATE2();
    int WS_AC_DATE = 0;
    int WS_LAST_AC_DATE = 0;
    int WS_NEXT_AC_DATE = 0;
    char WS_JRN_IN_USE = ' ';
    int WS_CAL_TMP1 = 0;
    int WS_CAL_TMP2 = 0;
    SOZCUTB_WS_CAL_TBL WS_CAL_TBL = new SOZCUTB_WS_CAL_TBL();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SOCMSG SOCMSG = new SOCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCRCWA SCRCWAT = new SCRCWA();
    SCRCWAT SCRPCWA = new SCRCWAT();
    BPCOQCAL BPCOQCAL = new BPCOQCAL();
    BPCOCLWD BPCOCLWD = new BPCOCLWD();
    TCCASMSG TCCASMSG = new TCCASMSG();
    SCCGWA SCCGWA;
    SCRCWAT SCRCWA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        if (pgmRtn) return;
        B000_MAIN_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "SOZCUTB return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGSCA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        T000_READ_UPD_SCTCWA();
        if (pgmRtn) return;
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        if (SCRPCWA.SYS_STUS != 'A') {
            WS_MSGID = SOCMSG.SYS_CLS;
            S000_ERROR_PROCESS();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, SCRPCWA.SYS_STUS);
        if (SCRPCWA.BUSS_MODE == 'O') {
            WS_MSGID = SOCMSG.SO_ERR_BUSS_OPEN;
            S000_ERROR_PROCESS();
            if (pgmRtn) return;
        }
        B100_REAL_PROCESS();
        if (pgmRtn) return;
    }
    public void B100_REAL_PROCESS() throws IOException,SQLException,Exception {
        WS_AC_DATE = SCRPCWA.AC_DATE_AREA[1-1].AC_DATE;
        IBS.CPY2CLS(SCCGWA, WS_AC_DATE+"", WS_CAL_DATE);
        R000_GET_M2();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "111111");
        IBS.init(SCCGWA, BPCOQCAL);
        BPCOQCAL.FUNC = '8';
        BPCOQCAL.BK = SCCGWA.COMM_AREA.TR_BANK;
        S000_CALL_BPZPQCAL();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "22222");
        IBS.init(SCCGWA, BPCOCLWD);
        BPCOCLWD.CAL_CODE = BPCOQCAL.CAL_CODE;
        BPCOCLWD.DAYE_FLG = 'N';
        BPCOCLWD.DATE1 = SCRPCWA.AC_DATE_AREA[1-1].AC_DATE;
        BPCOCLWD.WDAYS = 1;
        S000_CALL_BPZPCLWD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "33333");
        if (BPCOCLWD.DATE1 == BPCOCLWD.DATE2) {
            IBS.init(SCCGWA, BPCOCLWD);
            BPCOCLWD.CAL_CODE = BPCOQCAL.CAL_CODE;
            BPCOCLWD.DAYE_FLG = 'N';
            BPCOCLWD.DATE1 = SCRPCWA.AC_DATE_AREA[1-1].AC_DATE;
            BPCOCLWD.WDAYS = 2;
            S000_CALL_BPZPCLWD();
            if (pgmRtn) return;
        }
        WS_AC_DATE = SCRPCWA.AC_DATE_AREA[1-1].AC_DATE;
        IBS.CPY2CLS(SCCGWA, WS_AC_DATE+"", WS_TMP_DATE1);
        IBS.CPY2CLS(SCCGWA, BPCOCLWD.DATE2+"", WS_TMP_DATE2);
        CEP.TRC(SCCGWA, WS_TMP_DATE1);
        CEP.TRC(SCCGWA, WS_TMP_DATE2);
        CEP.TRC(SCCGWA, "44444");
        WS_LAST_AC_DATE = SCRPCWA.AC_DATE_AREA[1-1].AC_DATE;
        CEP.TRC(SCCGWA, WS_TMP_DATE2);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_TMP_DATE2);
        WS_AC_DATE = Integer.parseInt(JIBS_tmp_str[0]);
        IBS.init(SCCGWA, BPCOCLWD);
        BPCOCLWD.CAL_CODE = BPCOQCAL.CAL_CODE;
        BPCOCLWD.DAYE_FLG = 'N';
        BPCOCLWD.DATE1 = WS_AC_DATE;
        BPCOCLWD.WDAYS = 1;
        S000_CALL_BPZPCLWD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "5555");
        if (BPCOCLWD.DATE1 == BPCOCLWD.DATE2) {
            IBS.init(SCCGWA, BPCOCLWD);
            BPCOCLWD.CAL_CODE = BPCOQCAL.CAL_CODE;
            BPCOCLWD.DAYE_FLG = 'N';
            BPCOCLWD.DATE1 = WS_AC_DATE;
            BPCOCLWD.WDAYS = 2;
            S000_CALL_BPZPCLWD();
            if (pgmRtn) return;
        }
        IBS.CPY2CLS(SCCGWA, WS_AC_DATE+"", WS_TMP_DATE1);
        IBS.CPY2CLS(SCCGWA, BPCOCLWD.DATE2+"", WS_TMP_DATE2);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_TMP_DATE2);
        WS_NEXT_AC_DATE = Integer.parseInt(JIBS_tmp_str[0]);
        CEP.TRC(SCCGWA, "6666");
        if (SCRPCWA.JRN_IN_USE == '1') {
            WS_JRN_IN_USE = '2';
        } else {
            WS_JRN_IN_USE = '1';
        }
        SCRPCWA.AC_DATE_AREA[1-1].AC_DATE = WS_AC_DATE;
        SCRPCWA.AC_DATE_AREA[3-1].AC_DATE = WS_AC_DATE;
        SCRPCWA.AC_DATE_AREA[1-1].LAST_AC_DATE = WS_LAST_AC_DATE;
        SCRPCWA.AC_DATE_AREA[3-1].LAST_AC_DATE = WS_LAST_AC_DATE;
        SCRPCWA.AC_DATE_AREA[1-1].NEXT_AC_DATE = WS_NEXT_AC_DATE;
        SCRPCWA.AC_DATE_AREA[3-1].NEXT_AC_DATE = WS_NEXT_AC_DATE;
        SCRPCWA.JRN_IN_USE = WS_JRN_IN_USE;
        CEP.TRC(SCCGWA, "BEFORE-REWRITE-SCTCWA");
        CEP.TRC(SCCGWA, SCRPCWA.AC_DATE_AREA[1-1].AC_DATE);
        CEP.TRC(SCCGWA, SCRPCWA.AC_DATE_AREA[3-1].AC_DATE);
        CEP.TRC(SCCGWA, SCRPCWA.AC_DATE_AREA[1-1].LAST_AC_DATE);
        CEP.TRC(SCCGWA, SCRPCWA.AC_DATE_AREA[3-1].LAST_AC_DATE);
        CEP.TRC(SCCGWA, SCRPCWA.AC_DATE_AREA[1-1].NEXT_AC_DATE);
        CEP.TRC(SCCGWA, SCRPCWA.AC_DATE_AREA[3-1].NEXT_AC_DATE);
        T000_REWRITE_SCTCWA();
        if (pgmRtn) return;
    }
    public void R000_GET_M2() throws IOException,SQLException,Exception {
        WS_CAL_TMP2 = WS_CAL_DATE.WS_CAL_YEAR % 4;
        WS_CAL_TMP1 = (int) ((WS_CAL_DATE.WS_CAL_YEAR - WS_CAL_TMP2) / 4);
        if (WS_CAL_TMP2 != 0) {
            WS_CAL_TBL.WS_CAL_MD = 28;
            WS_CAL_TBL.WS_CAL_DX = "" + WS_CAL_TBL.WS_CAL_MD;
            JIBS_tmp_int = WS_CAL_TBL.WS_CAL_DX.length();
            for (int i=0;i<2-JIBS_tmp_int;i++) WS_CAL_TBL.WS_CAL_DX = "0" + WS_CAL_TBL.WS_CAL_DX;
        } else {
            WS_CAL_TMP2 = WS_CAL_DATE.WS_CAL_YEAR % 100;
            WS_CAL_TMP1 = (int) ((WS_CAL_DATE.WS_CAL_YEAR - WS_CAL_TMP2) / 100);
            if (WS_CAL_TMP2 != 0) {
                WS_CAL_TBL.WS_CAL_MD = 29;
                WS_CAL_TBL.WS_CAL_DX = "" + WS_CAL_TBL.WS_CAL_MD;
                JIBS_tmp_int = WS_CAL_TBL.WS_CAL_DX.length();
                for (int i=0;i<2-JIBS_tmp_int;i++) WS_CAL_TBL.WS_CAL_DX = "0" + WS_CAL_TBL.WS_CAL_DX;
            } else {
                WS_CAL_TMP2 = WS_CAL_DATE.WS_CAL_YEAR % 400;
                WS_CAL_TMP1 = (int) ((WS_CAL_DATE.WS_CAL_YEAR - WS_CAL_TMP2) / 400);
                if (WS_CAL_TMP2 == 0) {
                    WS_CAL_TBL.WS_CAL_MD = 29;
                    WS_CAL_TBL.WS_CAL_DX = "" + WS_CAL_TBL.WS_CAL_MD;
                    JIBS_tmp_int = WS_CAL_TBL.WS_CAL_DX.length();
                    for (int i=0;i<2-JIBS_tmp_int;i++) WS_CAL_TBL.WS_CAL_DX = "0" + WS_CAL_TBL.WS_CAL_DX;
                } else {
                    WS_CAL_TBL.WS_CAL_MD = 28;
                    WS_CAL_TBL.WS_CAL_DX = "" + WS_CAL_TBL.WS_CAL_MD;
                    JIBS_tmp_int = WS_CAL_TBL.WS_CAL_DX.length();
                    for (int i=0;i<2-JIBS_tmp_int;i++) WS_CAL_TBL.WS_CAL_DX = "0" + WS_CAL_TBL.WS_CAL_DX;
                }
            }
        }
    }
    public void R000_ADJ_DATE() throws IOException,SQLException,Exception {
        if (WS_TMP_DATE1.WS_TMP_MON1 != WS_TMP_DATE2.WS_TMP_MON2) {
            if (WS_TMP_DATE1.WS_TMP_DAY1 == WS_CAL_TBL.WS_CAL_MD) {
                WS_TMP_DATE2.WS_TMP_DAY2 = 1;
            } else {
                WS_TMP_DATE2.WS_TMP_YEAR2 = WS_TMP_DATE1.WS_TMP_YEAR1;
                WS_TMP_DATE2.WS_TMP_MON2 = WS_TMP_DATE1.WS_TMP_MON1;
                WS_TMP_DATE2.WS_TMP_DAY2 = WS_CAL_TBL.WS_CAL_MD;
            }
        }
    }
    public void S000_CALL_BPZPQCAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-CAL-CODE", BPCOQCAL);
        if (BPCOQCAL.RC.RC_CODE != 0) {
            WS_MSGID = SOCMSG.SO_ERR_CAL_CODE;
            S000_ERROR_PROCESS();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPCLWD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-CAL-WORK-DAY", BPCOCLWD);
        CEP.TRC(SCCGWA, BPCOCLWD);
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
