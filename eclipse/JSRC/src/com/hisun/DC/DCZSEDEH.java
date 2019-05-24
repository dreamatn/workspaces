package com.hisun.DC;

import com.hisun.BP.*;
import com.hisun.SC.*;
import com.hisun.DD.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZSEDEH {
    int JIBS_tmp_int;
    DBParm DCTCDDAT_RD;
    DBParm DCTINRCD_RD;
    DBParm DCTDCICT_RD;
    DBParm DCTICERD_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_OUTPUT_FMT_9 = "DC904";
    int K_MAX_COL = 99;
    int K_MAX_ROW = 50;
    int K_COL_CNT = 8;
    String K_HIS_REMARK = "E-CASH DEPOSIT EXCEPTION HANDLING APPLY";
    String K_HIS_COPYBOOK = "DCRICERD";
    String K_TBL_ICERD = "DCTICERD";
    String WS_ERR_MSG = " ";
    int WS_CNT = 0;
    double WS_NOT_INT_BAL = 0;
    double WS_CURR_BAL = 0;
    String WS_TEMP_CARD_NO = " ";
    String PGM_SCSSCLDT = "SCSSCLDT";
    char WS_TBL_FLAG = ' ';
    char WS_STOP_FIND_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DCRICERD DCRICERD = new DCRICERD();
    DCRCDDAT DCRCDDAT = new DCRCDDAT();
    SCCCLDT SCCCLDT = new SCCCLDT();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    DCCF904 DCCF904 = new DCCF904();
    DDCIQBAL DDCIQBAL = new DDCIQBAL();
    DCRDCICT DCRDCICT = new DCRDCICT();
    DCRINRCD DCRINRCD = new DCRINRCD();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DCCS9904 DCCS9904;
    public void MP(SCCGWA SCCGWA, DCCS9904 DCCS9904) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCS9904 = DCCS9904;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZSEDEH return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_APPLY_PROC();
        if (pgmRtn) return;
        B020_HISTORY_PROCESS();
        if (pgmRtn) return;
        B030_DATA_OUTPUT();
        if (pgmRtn) return;
    }
    public void B010_APPLY_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCS9904.CARD_NO);
        CEP.TRC(SCCGWA, DCCS9904.CARD_AMT);
        CEP.TRC(SCCGWA, DCCS9904.APP_COMPUTE_NUM);
        if (DCCS9904.CARD_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_NO_MISSING);
        }
        IBS.init(SCCGWA, DCRCDDAT);
        DCRCDDAT.KEY.CARD_NO = DCCS9904.CARD_NO;
        T000_READ_DCTCDDAT();
        if (pgmRtn) return;
        if (WS_TBL_FLAG == 'N') {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_NOT_EXIST);
        }
        if (DCRCDDAT.CARD_STS != 'N') {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_NOT_NORMAL_STS);
        }
        if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
        JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
        if (!DCRCDDAT.CARD_STSW.substring(16 - 1, 16 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_EC_NOT_OPEN);
        }
        if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
        JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
        if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
        JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
        if (DCRCDDAT.CARD_STSW.substring(11 - 1, 11 + 1 - 1).equalsIgnoreCase("1") 
            || DCRCDDAT.CARD_STSW.substring(12 - 1, 12 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_ALR_CARD_BACK_STS);
        }
        WS_STOP_FIND_FLAG = 'N';
        WS_TEMP_CARD_NO = DCCS9904.CARD_NO;
        while (WS_STOP_FIND_FLAG != 'Y') {
            IBS.init(SCCGWA, DCRINRCD);
            DCRINRCD.NEW_CARD_NO = WS_TEMP_CARD_NO;
            T000_READ_DCTINRCD();
            if (pgmRtn) return;
            if (WS_TBL_FLAG == 'Y' 
                && DCRINRCD.KEY.INCD_TYPE.equalsIgnoreCase("11") 
                && SCCGWA.COMM_AREA.AC_DATE < DCRINRCD.PEND_DT 
                && DCRINRCD.PEND_AMT > 0) {
                WS_STOP_FIND_FLAG = 'Y';
                CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_OLD_CARD_PEND_PROC);
            } else {
                WS_TEMP_CARD_NO = DCRINRCD.KEY.CARD_NO;
                IBS.init(SCCGWA, DCRICERD);
                DCRICERD.KEY.CARD_NO = WS_TEMP_CARD_NO;
                T000_READ_DCTICERD2();
                if (pgmRtn) return;
            }
        }
        IBS.init(SCCGWA, DDCIQBAL);
        DDCIQBAL.DATA.AC = DCCS9904.CARD_NO;
        DDCIQBAL.DATA.AID = "003";
        S000_CALL_DDZIQBAL();
        if (pgmRtn) return;
        WS_NOT_INT_BAL = DDCIQBAL.DATA.NINT_BAL;
        WS_CURR_BAL = DDCIQBAL.DATA.CURR_BAL;
        CEP.TRC(SCCGWA, WS_NOT_INT_BAL);
        CEP.TRC(SCCGWA, WS_CURR_BAL);
        if (WS_NOT_INT_BAL > 0) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_NOT_INIT_EXIST);
        }
        IBS.init(SCCGWA, DCRDCICT);
        DCRDCICT.CARD_NO = DCCS9904.CARD_NO;
        DCRDCICT.KEY.TXN_DT = SCCGWA.COMM_AREA.AC_DATE;
        DCRDCICT.WRITE_CARD_STS = '3';
        DCRDCICT.TXN_STS = '0';
        T000_READ_DCTDCICT();
        if (pgmRtn) return;
        if (WS_TBL_FLAG == 'Y') {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_W_CARD_ERROR_EXIST);
        }
        IBS.init(SCCGWA, DCRDCICT);
        DCRDCICT.CARD_NO = DCCS9904.CARD_NO;
        DCRDCICT.KEY.TXN_DT = SCCGWA.COMM_AREA.AC_DATE;
        DCRDCICT.WRITE_CARD_STS = '2';
        DCRDCICT.TXN_STS = '1';
        T000_READ_DCTDCICT();
        if (pgmRtn) return;
        if (WS_TBL_FLAG == 'Y') {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_QT_ERROR_EXIST);
        }
        IBS.init(SCCGWA, DCRICERD);
        DCRICERD.KEY.CARD_NO = DCCS9904.CARD_NO;
        T000_READ_DCTICERD();
        if (pgmRtn) return;
        if (WS_TBL_FLAG == 'N') {
            DCRICERD.KEY.SEQ = 1;
        } else {
            CEP.TRC(SCCGWA, DCRICERD.KEY.SEQ);
            DCRICERD.KEY.SEQ += 1;
        }
        IBS.init(SCCGWA, SCCCLDT);
        SCCCLDT.DATE1 = SCCGWA.COMM_AREA.AC_DATE;
        SCCCLDT.DAYS = 31;
        SCSSCLDT SCSSCLDT1 = new SCSSCLDT();
        SCSSCLDT1.MP(SCCGWA, SCCCLDT);
        CEP.TRC(SCCGWA, SCCCLDT.RC);
        if (SCCCLDT.RC == 0) {
            CEP.TRC(SCCGWA, SCCCLDT.DATE2);
            DCRICERD.NEXT_PROCESS_DT = SCCCLDT.DATE2;
        }
        DCRICERD.TXN_DT = SCCGWA.COMM_AREA.AC_DATE;
        DCRICERD.APP_COMPUTE_NUM = "" + DCCS9904.APP_COMPUTE_NUM;
        JIBS_tmp_int = DCRICERD.APP_COMPUTE_NUM.length();
        for (int i=0;i<4-JIBS_tmp_int;i++) DCRICERD.APP_COMPUTE_NUM = "0" + DCRICERD.APP_COMPUTE_NUM;
        DCCS9904.CARD_AMT = WS_CURR_BAL - DCCS9904.CARD_AMT;
        DCRICERD.CARD_AMT = DCCS9904.CARD_AMT;
        DCRICERD.NEXT_PROCESS_STS = '0';
        DCRICERD.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRICERD.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        DCRICERD.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRICERD.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_WRITE_DCTICERD();
        if (pgmRtn) return;
    }
    public void B020_HISTORY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_TOOL = DCCS9904.CARD_NO;
        BPCPNHIS.INFO.AC = DCCS9904.CARD_NO;
        BPCPNHIS.INFO.TX_CD = "0269904";
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARK;
        BPCPNHIS.INFO.FMT_ID = K_HIS_COPYBOOK;
        BPCPNHIS.INFO.FMT_ID_LEN = 116;
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.NEW_DAT_PT = DCRICERD;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B030_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        IBS.init(SCCGWA, DCCF904);
        DCCF904.CARD_NO = DCCS9904.CARD_NO;
        DCCF904.CARD_AMT = DCCS9904.CARD_AMT;
        DCCF904.APP_COMPUTE_NUM = DCCS9904.APP_COMPUTE_NUM;
        SCCFMT.FMTID = K_OUTPUT_FMT_9;
        SCCFMT.DATA_PTR = DCCF904;
        SCCFMT.DATA_LEN = 39;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS         ", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZIQBAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-INQ-CCY-BAL", DDCIQBAL);
        if (DDCIQBAL.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCIQBAL.RC);
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
    }
    public void T000_READ_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        IBS.READ(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        }
    }
    public void T000_READ_DCTINRCD() throws IOException,SQLException,Exception {
        DCTINRCD_RD = new DBParm();
        DCTINRCD_RD.TableName = "DCTINRCD";
        DCTINRCD_RD.where = "NEW_CARD_NO = :DCRINRCD.NEW_CARD_NO";
        IBS.READ(SCCGWA, DCRINRCD, this, DCTINRCD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
            WS_STOP_FIND_FLAG = 'Y';
        }
    }
    public void T000_READ_DCTDCICT() throws IOException,SQLException,Exception {
        DCTDCICT_RD = new DBParm();
        DCTDCICT_RD.TableName = "DCTDCICT";
        DCTDCICT_RD.where = "TXN_DT = :DCRDCICT.KEY.TXN_DT "
            + "AND CARD_NO = :DCRDCICT.CARD_NO "
            + "AND WRITE_CARD_STS = :DCRDCICT.WRITE_CARD_STS "
            + "AND TXN_STS = :DCRDCICT.TXN_STS";
        IBS.READ(SCCGWA, DCRDCICT, this, DCTDCICT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        }
    }
    public void T000_READ_DCTICERD() throws IOException,SQLException,Exception {
        DCTICERD_RD = new DBParm();
        DCTICERD_RD.TableName = "DCTICERD";
        DCTICERD_RD.where = "CARD_NO = :DCRICERD.KEY.CARD_NO";
        DCTICERD_RD.fst = true;
        DCTICERD_RD.order = "SEQ DESC";
        IBS.READ(SCCGWA, DCRICERD, this, DCTICERD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
            CEP.TRC(SCCGWA, DCRICERD.NEXT_PROCESS_STS);
            if (DCRICERD.NEXT_PROCESS_STS == '0') {
                CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_CANNOT_APPLY);
            }
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        }
    }
    public void T000_READ_DCTICERD2() throws IOException,SQLException,Exception {
        DCTICERD_RD = new DBParm();
        DCTICERD_RD.TableName = "DCTICERD";
        DCTICERD_RD.where = "CARD_NO = :DCRICERD.KEY.CARD_NO "
            + "AND NEXT_PROCESS_STS = '0'";
        DCTICERD_RD.fst = true;
        DCTICERD_RD.order = "SEQ DESC";
        IBS.READ(SCCGWA, DCRICERD, this, DCTICERD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_OLD_CARD_PEND_PROC2);
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        }
    }
    public void T000_WRITE_DCTICERD() throws IOException,SQLException,Exception {
        DCTICERD_RD = new DBParm();
        DCTICERD_RD.TableName = "DCTICERD";
        IBS.WRITE(SCCGWA, DCRICERD, DCTICERD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            WS_TBL_FLAG = 'D';
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void B_MPAG() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        JIBS_tmp_str[9] = "SCZMPAG";
        Class<?>clazz = Class.forName(JIBS_tmp_str[9].trim());
        Object obj = clazz.newInstance();
        Method m = clazz.getDeclaredMethod("MP",new Class[]{SCCGWA.getClass(), SCCMPAG.getClass()});
        m.invoke(obj, SCCGWA, SCCMPAG);
        if (SCCGWA.COMM_AREA.EXCP_FLG == 'Y') {
            Z_RET();
            if (pgmRtn) return;
        }
    } else { //FROM #ELSE
    } //FROM #ENDIF
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
