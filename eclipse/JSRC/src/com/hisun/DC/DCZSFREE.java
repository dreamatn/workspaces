package com.hisun.DC;

import com.hisun.BP.*;
import com.hisun.SC.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZSFREE {
    int JIBS_tmp_int;
    DBParm DCTCDDAT_RD;
    brParm DCTCDDAT_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String OUTPUT_FMT_9 = "DC803";
    int MAX_COL = 99;
    int MAX_ROW = 50;
    int COL_CNT = 8;
    String HIS_REMARK = "SINGLE ANNUAL FEE FREE MAINTENANCE";
    String HIS_COPYBOOK = "DCRCDDAT";
    String TBL_CDDAT = "DCTCDDAT";
    char WS_ANNUAL_FEE_FREE = 'Y';
    DCZSFREE_WS_VARIABLES WS_VARIABLES = new DCZSFREE_WS_VARIABLES();
    DCZSFREE_WS_FREE_OUTPUT WS_FREE_OUTPUT = new DCZSFREE_WS_FREE_OUTPUT();
    DCZSFREE_WS_SQL_VARIABLES WS_SQL_VARIABLES = new DCZSFREE_WS_SQL_VARIABLES();
    DCZSFREE_WS_CONDITION_FLAG WS_CONDITION_FLAG = new DCZSFREE_WS_CONDITION_FLAG();
    BPCMSG_ERROR_MSG ERROR_MSG = new BPCMSG_ERROR_MSG();
    DCCMSG_ERROR_MSG ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DCCUCINF DCCUCINF = new DCCUCINF();
    DCRCDDAT DCRCDDAT = new DCRCDDAT();
    DCRCDDAT DCRCDDAT = new DCRCDDAT();
    DCCUCDLP DCCUCDLP = new DCCUCDLP();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SC_AREA;
    SCCGBPA_BP_AREA BP_AREA;
    DCCSFREE DCCSFREE;
    public void MP(SCCGWA SCCGWA, DCCSFREE DCCSFREE) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCSFREE = DCCSFREE;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZSFREE return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, WS_VARIABLES);
        IBS.init(SCCGWA, WS_FREE_OUTPUT);
        IBS.init(SCCGWA, WS_SQL_VARIABLES);
        IBS.init(SCCGWA, DCRCDDAT);
        IBS.init(SCCGWA, DCRCDDAT);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCSFREE);
        CEP.TRC(SCCGWA, DCCSFREE.FUNC);
        if (DCCSFREE.FUNC == '1') {
            B010_CHECK_INPUT();
            if (pgmRtn) return;
            B011_ADDED_CHECK();
            if (pgmRtn) return;
            B012_ADDED_CHECK();
            if (pgmRtn) return;
            B020_APPLY_PROCESS();
            if (pgmRtn) return;
            B080_HISTORY_PROCESS();
            if (pgmRtn) return;
            B090_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (DCCSFREE.FUNC == '2') {
            B010_CHECK_INPUT();
            if (pgmRtn) return;
            B020_MODIFY_PROCESS();
            if (pgmRtn) return;
            B090_DATA_OUTPUT();
            if (pgmRtn) return;
        } else {
            CEP.ERR(SCCGWA, ERROR_MSG.DC_FUNC_ERR);
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (DCCSFREE.CARD_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, ERROR_MSG.DC_MUST_INPUT_CARD_NO);
        }
        IBS.init(SCCGWA, DCCUCINF);
        DCCUCINF.CARD_NO = DCCSFREE.CARD_NO;
        S000_CALL_DCZUCINF();
        if (pgmRtn) return;
        WS_SQL_VARIABLES.HLDR_CINO = DCCUCINF.CARD_HLDR_CINO;
        CEP.TRC(SCCGWA, DCCUCINF.CARD_STS);
        if (DCCUCINF.CARD_STS != 'N') {
            CEP.ERR(SCCGWA, ERROR_MSG.DC_CARD_NOT_NORMAL_STS);
        }
        if (DCCUCINF.CARD_STSW == null) DCCUCINF.CARD_STSW = "";
        JIBS_tmp_int = DCCUCINF.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCCUCINF.CARD_STSW += " ";
        if (DCCUCINF.CARD_STSW == null) DCCUCINF.CARD_STSW = "";
        JIBS_tmp_int = DCCUCINF.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCCUCINF.CARD_STSW += " ";
        if (DCCUCINF.CARD_STSW.substring(0, 1).equalsIgnoreCase("1") 
            || DCCUCINF.CARD_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.ERR(SCCGWA, ERROR_MSG.DC_ALR_CARD_LOSS_STS);
        }
        if (DCCUCINF.CARD_STSW == null) DCCUCINF.CARD_STSW = "";
        JIBS_tmp_int = DCCUCINF.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCCUCINF.CARD_STSW += " ";
        if (DCCUCINF.CARD_STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1")) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_CARD_CVN_ERR_LOCK;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCUCINF.CARD_STSW == null) DCCUCINF.CARD_STSW = "";
        JIBS_tmp_int = DCCUCINF.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCCUCINF.CARD_STSW += " ";
        if (DCCUCINF.CARD_STSW.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("1")) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_CARD_PIN_LOCK;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCUCINF.CARD_STSW == null) DCCUCINF.CARD_STSW = "";
        JIBS_tmp_int = DCCUCINF.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCCUCINF.CARD_STSW += " ";
        if (DCCUCINF.CARD_STSW.substring(10 - 1, 10 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.ERR(SCCGWA, ERROR_MSG.DC_ALR_CARD_SWALLOW_STS);
        }
        if ((SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
            || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
            || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102"))) {
            IBS.init(SCCGWA, DCCUCDLP);
            DCCUCDLP.CARD_NO = DCCSFREE.CARD_NO;
            DCCUCDLP.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            S000_CALL_DCZUCDLP();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, DCCUCINF.CARD_PROD_FLG);
        if (DCCUCINF.CARD_PROD_FLG == 'S') {
            CEP.ERR(SCCGWA, ERROR_MSG.DC_CITY_NO_APPLY_YFEE);
        }
        CEP.TRC(SCCGWA, DCCUCINF.JOIN_CUS_FLG);
        if (DCCUCINF.JOIN_CUS_FLG == 'Y') {
            CEP.ERR(SCCGWA, ERROR_MSG.DC_JOICARD_NOALLOW_YFEE);
        }
        if (DCCUCINF.ADSC_TYPE == 'C') {
            CEP.ERR(SCCGWA, ERROR_MSG.DC_UNICARD_NOALLOW_YFEE);
        }
    }
    public void B011_ADDED_CHECK() throws IOException,SQLException,Exception {
        if (DCCUCINF.ANNUAL_FEE_FREE == 'Y') {
            CEP.ERR(SCCGWA, ERROR_MSG.DC_CARD_ANNUAL_FEE_YES);
        }
    }
    public void B012_ADDED_CHECK() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCSFREE.CARD_NO);
        CEP.TRC(SCCGWA, WS_SQL_VARIABLES.HLDR_CINO);
        CEP.TRC(SCCGWA, DCCUCINF.ISSU_BR);
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = DCCUCINF.ISSU_BR;
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "SFREE-CARD-VIL-TYP");
        WS_VARIABLES.CARD_VIL_TYP_1 = BPCPQORG.VIL_TYP;
        CEP.TRC(SCCGWA, WS_VARIABLES.CARD_VIL_TYP_1);
        IBS.init(SCCGWA, DCRCDDAT);
        WS_VARIABLES.CNT = 0;
        T000_STARTBR_DCTCDDAT();
        if (pgmRtn) return;
        T000_READNEXT_DCTCDDAT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        while (WS_CONDITION_FLAG.TBL_FLAG != 'N') {
            IBS.init(SCCGWA, BPCPQORG);
            WS_VARIABLES.CARD_VIL_TYP_2 = " ";
            BPCPQORG.BR = DCRCDDAT.ISSU_BR;
            S000_CALL_BPZPQORG();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, "CARD-VIL-TYP");
            WS_VARIABLES.CARD_VIL_TYP_2 = BPCPQORG.VIL_TYP;
            CEP.TRC(SCCGWA, WS_VARIABLES.CARD_VIL_TYP_2);
            WS_VARIABLES.FREE_FEE_BV_CD = DCRCDDAT.BV_CD_NO;
            if (DCRCDDAT.CARD_TYP == null) DCRCDDAT.CARD_TYP = "";
            JIBS_tmp_int = DCRCDDAT.CARD_TYP.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) DCRCDDAT.CARD_TYP += " ";
            if (DCRCDDAT.CARD_TYP == null) DCRCDDAT.CARD_TYP = "";
            JIBS_tmp_int = DCRCDDAT.CARD_TYP.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) DCRCDDAT.CARD_TYP += " ";
            if (DCRCDDAT.CARD_TYP == null) DCRCDDAT.CARD_TYP = "";
            JIBS_tmp_int = DCRCDDAT.CARD_TYP.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) DCRCDDAT.CARD_TYP += " ";
            if (DCRCDDAT.CARD_TYP == null) DCRCDDAT.CARD_TYP = "";
            JIBS_tmp_int = DCRCDDAT.CARD_TYP.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) DCRCDDAT.CARD_TYP += " ";
            if (DCRCDDAT.KEY.CARD_NO == null) DCRCDDAT.KEY.CARD_NO = "";
            JIBS_tmp_int = DCRCDDAT.KEY.CARD_NO.length();
            for (int i=0;i<19-JIBS_tmp_int;i++) DCRCDDAT.KEY.CARD_NO += " ";
            if ((DCRCDDAT.CARD_TYP.substring(0, 1).equalsIgnoreCase("1") 
                || DCRCDDAT.CARD_TYP.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1") 
                || DCRCDDAT.CARD_TYP.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1") 
                || DCRCDDAT.CARD_TYP.substring(6 - 1, 6 + 1 - 1).equalsIgnoreCase("1") 
                || (WS_VARIABLES.FREE_FEE_BV_CD.equalsIgnoreCase("432") 
                || WS_VARIABLES.FREE_FEE_BV_CD.equalsIgnoreCase("168") 
                || WS_VARIABLES.FREE_FEE_BV_CD.equalsIgnoreCase("426") 
                || WS_VARIABLES.FREE_FEE_BV_CD.equalsIgnoreCase("431") 
                || WS_VARIABLES.FREE_FEE_BV_CD.equalsIgnoreCase("429") 
                || WS_VARIABLES.FREE_FEE_BV_CD.equalsIgnoreCase("036") 
                || WS_VARIABLES.FREE_FEE_BV_CD.equalsIgnoreCase("425") 
                || WS_VARIABLES.FREE_FEE_BV_CD.equalsIgnoreCase("430")) 
                || DCRCDDAT.ANNUAL_FEE_FREE == 'Y') 
                && !DCRCDDAT.KEY.CARD_NO.substring(0, 10).equalsIgnoreCase("6214619999") 
                && WS_VARIABLES.CARD_VIL_TYP_2.equalsIgnoreCase(WS_VARIABLES.CARD_VIL_TYP_1)) {
                WS_VARIABLES.CNT += 1;
            }
            T000_READNEXT_DCTCDDAT();
            if (pgmRtn) return;
        }
        T000_ENDBR_DCTCDDAT();
        if (pgmRtn) return;
        if (WS_VARIABLES.CNT >= 1) {
            CEP.ERR(SCCGWA, ERROR_MSG.DC_CARD_ONLY_ONE_FREE);
        }
    }
    public void B020_APPLY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRCDDAT);
        DCRCDDAT.KEY.CARD_NO = DCCSFREE.CARD_NO;
        T000_READ_DCTCDDAT_UPD();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, DCRCDDAT, DCRCDDAT);
        DCRCDDAT.ANU_FEE_FREE = 999;
        DCRCDDAT.ANNUAL_FEE_FREE = 'Y';
        DCRCDDAT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRCDDAT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_REWRITE_DCTCDDAT();
        if (pgmRtn) return;
    }
    public void B020_MODIFY_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "OLD CARD HLDR CINO");
        CEP.TRC(SCCGWA, DCCUCINF.CARD_HLDR_CINO);
        WS_VARIABLES.OLD_CARD_HLDR_CINO = DCCUCINF.CARD_HLDR_CINO;
        CEP.TRC(SCCGWA, DCCUCINF.ANNUAL_FEE_FREE);
        if (DCCUCINF.ANNUAL_FEE_FREE != 'Y') {
            CEP.ERR(SCCGWA, ERROR_MSG.DC_CARD_ANNUAL_FEE_NOEX);
        } else {
            WS_VARIABLES.TEMP_CARD_NO = DCCSFREE.CARD_NO;
            DCCSFREE.CARD_NO = " ";
            DCCSFREE.CARD_NO = DCCSFREE.NEW_CARD_NO;
            B010_CHECK_INPUT();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, "NEW CARD-HLDR-CINO");
            CEP.TRC(SCCGWA, DCCUCINF.CARD_HLDR_CINO);
            if (!DCCUCINF.CARD_HLDR_CINO.equalsIgnoreCase(WS_VARIABLES.OLD_CARD_HLDR_CINO)) {
                CEP.ERR(SCCGWA, ERROR_MSG.DC_CARD_HLR_DIFFERENT);
            }
            B011_ADDED_CHECK();
            if (pgmRtn) return;
            B020_APPLY_PROCESS();
            if (pgmRtn) return;
            B080_HISTORY_PROCESS();
            if (pgmRtn) return;
            IBS.init(SCCGWA, DCRCDDAT);
            CEP.TRC(SCCGWA, WS_VARIABLES.TEMP_CARD_NO);
            DCRCDDAT.KEY.CARD_NO = WS_VARIABLES.TEMP_CARD_NO;
            DCCSFREE.CARD_NO = WS_VARIABLES.TEMP_CARD_NO;
            T000_READ_DCTCDDAT_UPD();
            if (pgmRtn) return;
            IBS.CLONE(SCCGWA, DCRCDDAT, DCRCDDAT);
            DCRCDDAT.ANU_FEE_FREE = 0;
            DCRCDDAT.ANNUAL_FEE_FREE = 'N';
            DCRCDDAT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DCRCDDAT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_REWRITE_DCTCDDAT();
            if (pgmRtn) return;
            B080_HISTORY_PROCESS();
            if (pgmRtn) return;
        }
    }
    public void B080_HISTORY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.AC = DCCSFREE.CARD_NO;
        BPCPNHIS.INFO.TX_TOOL = DCCSFREE.CARD_NO;
        BPCPNHIS.INFO.TX_TYP_CD = "PB08";
        BPCPNHIS.INFO.TX_RMK = HIS_REMARK;
        BPCPNHIS.INFO.FMT_ID = HIS_COPYBOOK;
        BPCPNHIS.INFO.FMT_ID_LEN = 489;
        if (DCCSFREE.FUNC == '1') {
            BPCPNHIS.INFO.TX_TYP = 'A';
            BPCPNHIS.INFO.DATA_FLG = 'Y';
            BPCPNHIS.INFO.NEW_DAT_PT = DCRCDDAT;
        }
        if (DCCSFREE.FUNC == '2') {
            BPCPNHIS.INFO.TX_TYP = 'M';
            BPCPNHIS.INFO.DATA_FLG = 'Y';
            BPCPNHIS.INFO.NEW_DAT_PT = DCRCDDAT;
            BPCPNHIS.INFO.OLD_DAT_PT = DCRCDDAT;
        }
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B090_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        WS_FREE_OUTPUT.CARD_NO = DCCSFREE.CARD_NO.charAt(0);
        SCCFMT.FMTID = OUTPUT_FMT_9;
        SCCFMT.DATA_PTR = WS_FREE_OUTPUT;
        SCCFMT.DATA_LEN = 1;
        CEP.TRC(SCCGWA, WS_FREE_OUTPUT);
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_DCZUCINF() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "N001");
        IBS.CALLCPN(SCCGWA, "DC-U-CARD-INF", DCCUCINF);
        CEP.TRC(SCCGWA, "N002");
        if (DCCUCINF.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, DCCUCINF.RC);
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCUCINF.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "N003");
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS         ", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZUCDLP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-CHECK-BR", DCCUCDLP);
        if (DCCUCDLP.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCUCDLP.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_VARIABLES.ERR_MSG);
    }
    public void T000_READ_DCTCDDAT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCRCDDAT.KEY.CARD_NO);
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        IBS.READ(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, ERROR_MSG.DC_CARD_NOT_EXIST);
        }
    }
    public void T000_READ_DCTCDDAT_UPD() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCRCDDAT.KEY.CARD_NO);
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        DCTCDDAT_RD.upd = true;
        IBS.READ(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
    }
    public void T000_STARTBR_DCTCDDAT() throws IOException,SQLException,Exception {
        WS_SQL_VARIABLES.CARD_NO_TEMP = DCCSFREE.CARD_NO;
        DCTCDDAT_BR.rp = new DBParm();
        DCTCDDAT_BR.rp.TableName = "DCTCDDAT";
        DCTCDDAT_BR.rp.where = "CARD_HLDR_CINO = :WS_SQL_VARIABLES.HLDR_CINO "
            + "AND CARD_STS = 'N' "
            + "AND CARD_NO < > :WS_SQL_VARIABLES.CARD_NO_TEMP";
        IBS.STARTBR(SCCGWA, DCRCDDAT, this, DCTCDDAT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_CONDITION_FLAG.TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            WS_CONDITION_FLAG.TBL_FLAG = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_CDDAT;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "REWRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READNEXT_DCTCDDAT() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DCRCDDAT, this, DCTCDDAT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_CONDITION_FLAG.TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_CONDITION_FLAG.TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_CDDAT;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_DCTCDDAT() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DCTCDDAT_BR);
    }
    public void T000_REWRITE_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        DCTCDDAT_RD.errhdl = true;
        IBS.REWRITE(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_CONDITION_FLAG.TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            WS_CONDITION_FLAG.TBL_FLAG = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_CDDAT;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "REWRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
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
