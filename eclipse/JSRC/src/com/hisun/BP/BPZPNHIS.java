package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZPNHIS {
    int JIBS_tmp_int;
    DBParm BPTTLT_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_PROC_BPTNHIST = "BP-R-PROC-NHIST";
    String CPN_PROC_NHIS_DET = "BP-R-WRIT-NHISD";
    String PGM_SCZTIME = "SCZTIME";
    char WS_EMP_RECORD = ' ';
    String WS_PGM = " ";
    String WS_ERR_MSG = " ";
    char WS_TBL_BANK_FLAG = ' ';
    char WS_TLT_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    BPRNHIST BPRNHIST = new BPRNHIST();
    SCCTIME SCCTIME = new SCCTIME();
    BPCTHIST BPCTHIST = new BPCTHIST();
    BPCTDCWH BPCTDCWH = new BPCTDCWH();
    BPRTLT BPRTLT = new BPRTLT();
    SCCMSG SCCMSG = new SCCMSG();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    SCCGMSG SCCGMSG;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCPNHIS BPCPNHIS;
    String WS_NEW_REC = " ";
    String WS_OLD_REC = " ";
    public void MP(SCCGWA SCCGWA, BPCPNHIS BPCPNHIS) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCPNHIS = BPCPNHIS;
        CEP.TRC(SCCGWA);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.MSG_PROC_AREA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZPNHIS return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGSCA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SCCGMSG = (SCCGMSG) SCCGSCA_SC_AREA.MSG_AREA_PTR;
        WS_OLD_REC = IBS.CLS2CPY(SCCGWA, BPCPNHIS.INFO.OLD_DAT_PT);
        WS_NEW_REC = IBS.CLS2CPY(SCCGWA, BPCPNHIS.INFO.NEW_DAT_PT);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            B010_CHECK_INPUT_DATA();
            if (pgmRtn) return;
            B020_RECORD_NHIS();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, "AAAAA");
        } else {
            B030_RECORD_NHIS_CANCEL();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, "HELLO");
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (BPCPNHIS.INFO.TX_TYP != 'A' 
            && BPCPNHIS.INFO.TX_TYP != 'D' 
            && BPCPNHIS.INFO.TX_TYP != 'M' 
            && BPCPNHIS.INFO.TX_TYP != 'O') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_HIS_TX_TYP_ERR, BPCPNHIS.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCPNHIS.INFO.FMT_ID);
        if (BPCPNHIS.INFO.TX_TYP == 'M' 
            && BPCPNHIS.INFO.FMT_ID.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_HIS_MUST_INP_FMT_ID, BPCPNHIS.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (SCCGWA.COMM_AREA.TL_ID.trim().length() > 0) {
            IBS.init(SCCGWA, BPRTLT);
            BPRTLT.KEY.TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_READ_BPTTLT();
            if (pgmRtn) return;
            if (WS_TLT_FLG == 'Y') {
                CEP.TRC(SCCGWA, BPRTLT.CI_NO);
                if (BPRTLT.CI_NO.equalsIgnoreCase(BPCPNHIS.INFO.CI_NO) 
                    && BPCPNHIS.INFO.CI_NO.trim().length() > 0 
                    && BPRTLT.CI_NO.trim().length() > 0) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.M_TLR_NOT_MOD_OWNIFO;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
        }
    }
    public void B020_RECORD_NHIS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.CHNL);
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
    } else { //FROM #ELSE
        if (SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT") 
            && SCCGWA.COMM_AREA.JRN_NO == 0) {
            IBS.init(SCCGWA, SCCCALL);
            SCCCALL.CPN_NAME = "SC-GET-JOURNAL-NO";
            SCCCALL.COMMAREA_PTR = null;
            SCCCALL.CONTINUE_FLG = 'Y';
            IBS.CALL(SCCGWA, SCCCALL);
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
    } //FROM #ENDIF
        if (SCCGWA.COMM_AREA.JRN_NO == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_HIS_JRNNO_ERR, BPCPNHIS.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPRNHIST);
        IBS.init(SCCGWA, BPCTHIST);
        BPCTHIST.PTR = BPRNHIST;
        BPCTHIST.LEN = 436;
        BPRNHIST.KEY.AC_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPRNHIST.UPDATE_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPRNHIST.KEY.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        CEP.TRC(SCCGWA, BPRNHIST.KEY.AC_DT);
        CEP.TRC(SCCGWA, BPRNHIST.KEY.JRNNO);
        GWA_BP_AREA.NFHIS_CUR_SEQ = (short) (GWA_BP_AREA.NFHIS_CUR_SEQ + 1);
        BPRNHIST.KEY.JRN_SEQ = GWA_BP_AREA.NFHIS_CUR_SEQ;
        CEP.TRC(SCCGWA, GWA_BP_AREA.NFHIS_CUR_SEQ);
        if (SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT") 
            || SCCGWA.COMM_AREA.TR_DATE == 0) {
            S00_GET_TIME();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, SCCTIME.YYYYMMDD);
            CEP.TRC(SCCGWA, SCCTIME.HHMMSS);
            BPRNHIST.TX_DT = SCCTIME.YYYYMMDD;
            BPRNHIST.TX_TM = SCCTIME.HHMMSS;
        } else {
            BPRNHIST.TX_DT = SCCGWA.COMM_AREA.TR_DATE;
            BPRNHIST.TX_TM = SCCGWA.COMM_AREA.TR_TIME;
        }
        BPRNHIST.CCY = BPCPNHIS.INFO.CCY;
        BPRNHIST.CCY_TYPE = BPCPNHIS.INFO.CCY_TYPE;
        BPRNHIST.AC_SEQ = BPCPNHIS.INFO.AC_SEQ;
        BPRNHIST.KEY.AC_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPRNHIST.TX_TYP = BPCPNHIS.INFO.TX_TYP;
        BPRNHIST.AC = BPCPNHIS.INFO.AC;
        CEP.TRC(SCCGWA, BPCPNHIS.INFO.AC);
        BPRNHIST.REF_NO = BPCPNHIS.INFO.REF_NO;
        BPRNHIST.TX_TOOL = BPCPNHIS.INFO.TX_TOOL;
        CEP.TRC(SCCGWA, BPCPNHIS.INFO.TX_TYP_CD);
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y' 
            || SCCGWA.COMM_AREA.REVERSAL_IND == 'Y') {
            BPRNHIST.TX_TYP_CD = "G004";
        } else {
            if (BPCPNHIS.INFO.TX_TYP_CD.trim().length() > 0) {
                BPRNHIST.TX_TYP_CD = BPCPNHIS.INFO.TX_TYP_CD;
            } else {
                BPRNHIST.TX_TYP_CD = SCCGWA.COMM_AREA.TR_MMO;
            }
        }
        CEP.TRC(SCCGWA, BPCPNHIS.INFO.CI_NO);
        BPRNHIST.CI_NO = BPCPNHIS.INFO.CI_NO;
        BPRNHIST.MAKER_TLR = BPCPNHIS.INFO.MAKER_TLR;
        if (BPCPNHIS.INFO.TX_CD.trim().length() > 0) {
            CEP.TRC(SCCGWA, BPCPNHIS.INFO.TX_CD);
            BPRNHIST.TX_CD = BPCPNHIS.INFO.TX_CD;
        } else {
            BPRNHIST.TX_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        }
        BPRNHIST.TX_STS = 'N';
        BPRNHIST.TX_RMK = BPCPNHIS.INFO.TX_RMK;
        BPRNHIST.FMT_ID = BPCPNHIS.INFO.FMT_ID;
        if (BPCPNHIS.INFO.TX_VAL_DT > 0) {
            BPRNHIST.TX_VAL_DT = BPCPNHIS.INFO.TX_VAL_DT;
            CEP.TRC(SCCGWA, BPCPNHIS.INFO.TX_VAL_DT);
        }
        BPRNHIST.APP_MMO = SCCGWA.COMM_AREA.AP_EXT_MMO;
        BPRNHIST.TX_CHNL = SCCGWA.COMM_AREA.CHNL;
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            BPRNHIST.TX_REQ_CHNL = SCCGWA.COMM_AREA.REQ_CHNL2;
            BPRNHIST.TX_CHNL_DTL = SCCGWA.COMM_AREA.CHNL_DTL;
            BPRNHIST.UPDATE_DT = SCCGWA.COMM_AREA.AC_DATE;
        }
        BPRNHIST.TX_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPRNHIST.TX_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRNHIST.TX_DP = SCCGWA.COMM_AREA.BR_DP.TR_DEP;
        CEP.TRC(SCCGWA, SCCGMSG.SUP1_ID);
        BPRNHIST.SUP1 = SCCGWA.COMM_AREA.SUP1_ID;
        CEP.TRC(SCCGWA, SCCGMSG.SUP2_ID);
        BPRNHIST.SUP2 = SCCGWA.COMM_AREA.SUP2_ID;
        CEP.TRC(SCCGWA, BPRNHIST.TX_DT);
        CEP.TRC(SCCGWA, BPRNHIST.TX_TM);
        CEP.TRC(SCCGWA, BPRNHIST.KEY.AC_DT);
        CEP.TRC(SCCGWA, BPRNHIST.TX_TYP);
        CEP.TRC(SCCGWA, BPRNHIST.AC);
        CEP.TRC(SCCGWA, BPRNHIST.REF_NO);
        CEP.TRC(SCCGWA, BPRNHIST.TX_TOOL);
        CEP.TRC(SCCGWA, BPRNHIST.TX_TYP_CD);
        CEP.TRC(SCCGWA, BPRNHIST.CI_NO);
        CEP.TRC(SCCGWA, BPRNHIST.MAKER_TLR);
        CEP.TRC(SCCGWA, BPRNHIST.TX_CD);
        CEP.TRC(SCCGWA, BPRNHIST.TX_RMK);
        CEP.TRC(SCCGWA, BPRNHIST.APP_MMO);
        CEP.TRC(SCCGWA, BPRNHIST.TX_CHNL);
        CEP.TRC(SCCGWA, BPRNHIST.TX_TLR);
        CEP.TRC(SCCGWA, BPRNHIST.TX_BR);
        CEP.TRC(SCCGWA, BPRNHIST.TX_DP);
        CEP.TRC(SCCGWA, BPRNHIST.SUP1);
        CEP.TRC(SCCGWA, BPRNHIST.SUP2);
        CEP.TRC(SCCGWA, BPCPNHIS.INFO.FMT_ID_LEN);
        CEP.TRC(SCCGWA, BPCPNHIS.INFO.DATA_FLG);
        if (BPCPNHIS.INFO.DATA_FLG == 'Y') {
            if (BPCPNHIS.INFO.OLD_DAT_PT != null) {
                CEP.TRC(SCCGWA, "OLD DAT NOT NULL");
                CEP.TRC(SCCGWA, BPRNHIST.BLOB_OLD_DATA.trim().length());
                BPRNHIST.BLOB_OLD_DATA = WS_OLD_REC;
                CEP.TRC(SCCGWA, "FINISH OLD DAT!!!");
            }
            if (BPCPNHIS.INFO.NEW_DAT_PT != null) {
                CEP.TRC(SCCGWA, "NEW DAT NOT NULL");
                CEP.TRC(SCCGWA, BPRNHIST.BLOB_NEW_DATA.trim().length());
                BPRNHIST.BLOB_NEW_DATA = WS_NEW_REC;
                CEP.TRC(SCCGWA, "FINISH NEW DAT!!!");
            }
        }
        CEP.TRC(SCCGWA, "FINISH");
        BPCTHIST.PTR = BPRNHIST;
        BPCTHIST.LEN = 436;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.MSG_PROC_AREA);
        CEP.TRC(SCCGWA, BPRNHIST);
        BPCTHIST.INFO.FUNC = '5';
        S000_CALL_BPZTHIST();
        if (pgmRtn) return;
        if (BPCTHIST.RETURN_INFO == 'D') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_HIS_RECORD_EXIST, BPCPNHIS.RC);
        }
        if (BPCPNHIS.INFO.TX_TYP == 'M' 
            || BPCPNHIS.INFO.DATA_FLG == 'Y') {
            IBS.init(SCCGWA, BPCTDCWH);
            BPCTDCWH.TR_JRNNO = BPRNHIST.KEY.JRNNO;
            BPCTDCWH.AC_DT = BPRNHIST.KEY.AC_DT;
            BPCTDCWH.JRN_SEQ = BPRNHIST.KEY.JRN_SEQ;
            BPCTDCWH.FMT_ID = BPCPNHIS.INFO.FMT_ID;
            BPCTDCWH.DATA_OLD = BPCPNHIS.INFO.OLD_DAT_PT;
            BPCTDCWH.DATA_NEW = BPCPNHIS.INFO.NEW_DAT_PT;
            if (BPCPNHIS.INFO.TX_TYP == 'A') {
                if (BPCTDCWH.DATA_NEW == null) {
                    IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCPNHIS.RC);
                    Z_RET();
                    if (pgmRtn) return;
                }
                BPCTDCWH.DATA_OLD = null;
            }
            if (BPCPNHIS.INFO.TX_TYP == 'D') {
                if (BPCTDCWH.DATA_OLD == null) {
                    IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCPNHIS.RC);
                    Z_RET();
                    if (pgmRtn) return;
                }
                BPCTDCWH.DATA_NEW = null;
            }
            if (BPCPNHIS.INFO.FMT_ID == null) BPCPNHIS.INFO.FMT_ID = "";
            JIBS_tmp_int = BPCPNHIS.INFO.FMT_ID.length();
            for (int i=0;i<8-JIBS_tmp_int;i++) BPCPNHIS.INFO.FMT_ID += " ";
            if (WS_PGM == null) WS_PGM = "";
            JIBS_tmp_int = WS_PGM.length();
            for (int i=0;i<8-JIBS_tmp_int;i++) WS_PGM += " ";
            WS_PGM = BPCPNHIS.INFO.FMT_ID.substring(0, 2) + WS_PGM.substring(2);
            if (WS_PGM == null) WS_PGM = "";
            JIBS_tmp_int = WS_PGM.length();
            for (int i=0;i<8-JIBS_tmp_int;i++) WS_PGM += " ";
            WS_PGM = WS_PGM.substring(0, 3 - 1) + "ZTDCWH" + WS_PGM.substring(3 + "ZTDCWH".length() - 1);
            CEP.TRC(SCCGWA, WS_PGM);
            CEP.TRC(SCCGWA, BPCPNHIS.INFO.FMT_ID);
        }
    }
    public void B030_RECORD_NHIS_CANCEL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRNHIST);
        IBS.init(SCCGWA, BPCTHIST);
        BPRNHIST.KEY.AC_DT = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
        BPRNHIST.UPDATE_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPRNHIST.KEY.JRNNO = SCCGWA.COMM_AREA.CANCEL_JRN_NO;
        BPCTHIST.PTR = BPRNHIST;
        BPCTHIST.LEN = 436;
        BPCTHIST.INFO.FUNC = '8';
        S000_CALL_BPZTHIST();
        if (pgmRtn) return;
        BPCTHIST.PTR = BPRNHIST;
        BPCTHIST.LEN = 436;
        BPCTHIST.INFO.FUNC = '2';
        S000_CALL_BPZTHIST();
        if (pgmRtn) return;
        while (BPCTHIST.RETURN_INFO != 'N') {
            BPCTHIST.INFO.FUNC = '9';
            BPRNHIST.TX_STS = 'C';
            BPRNHIST.TX_REV_DT = SCCGWA.COMM_AREA.AC_DATE;
            BPCTHIST.PTR = BPRNHIST;
            BPCTHIST.LEN = 436;
            S000_CALL_BPZTHIST();
            if (pgmRtn) return;
            BPCTHIST.LEN = 436;
            BPCTHIST.PTR = BPRNHIST;
            BPCTHIST.INFO.FUNC = '2';
            S000_CALL_BPZTHIST();
            if (pgmRtn) return;
        }
        BPCTHIST.PTR = BPRNHIST;
        BPCTHIST.LEN = 436;
        BPCTHIST.INFO.FUNC = '3';
        S000_CALL_BPZTHIST();
        if (pgmRtn) return;
    }
    public void T000_READ_BPTTLT() throws IOException,SQLException,Exception {
        BPTTLT_RD = new DBParm();
        BPTTLT_RD.TableName = "BPTTLT";
        IBS.READ(SCCGWA, BPRTLT, BPTTLT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TLT_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TLT_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTTLT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZTHIST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_PROC_BPTNHIST, BPCTHIST);
        if (BPCTHIST.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCTHIST.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCPNHIS.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S00_GET_TIME() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCTIME);
        SCZTIME SCZTIME = new SCZTIME();
        SCZTIME.MP(SCCGWA, SCCTIME);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCPNHIS.RC);
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCPNHIS.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCPNHIS = ");
            CEP.TRC(SCCGWA, BPCPNHIS);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
