package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.DC.*;
import com.hisun.CI.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT1251 {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    String JIBS_NumStr;
    String JIBS_f0;
    brParm BPTFEHIS_BR = new brParm();
    boolean pgmRtn = false;
    String CPN_TLR_INF_QUERY = "BP-F-TLR-INF-QUERY  ";
    String CPN_P_SET_SBUS_TRN = "SC-SET-SUBS-TRANS";
    String K_OUTPUT_FMT = "BPA56";
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG      ";
    String CPN_INQUIRE_CCY = "BP-INQUIRE-CCY    ";
    String CPN_P_INQ_PC = "BP-P-INQ-PC       ";
    String K_RBASE = "RBASE";
    String K_RTENO = "RTENO";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    short WS_I = 0;
    int WS_BR = 0;
    String WS_CCY = " ";
    String WS_BASE_TYP = " ";
    String WS_TENOR = " ";
    char WS_FRZ_FLG = ' ';
    int WS_STR_DT = 0;
    int WS_END_DT = 0;
    int WS_SET_DT = 0;
    int WS_SET_TM = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCSUBS SCCSUBS = new SCCSUBS();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCOTLRQ BPCOTLRQ = new BPCOTLRQ();
    BPCFEHIT BPCFEHIT = new BPCFEHIT();
    BPRFEHIS BPRFEHIS = new BPRFEHIS();
    DCCPACTY DCCPACTY = new DCCPACTY();
    CICACCU CICACCU = new CICACCU();
    SCCGWA SCCGWA;
    BPB1251_AWA_1251 BPB1251_AWA_1251;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPOT1251 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB1251_AWA_1251>");
        BPB1251_AWA_1251 = (BPB1251_AWA_1251) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_MAIN_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPB1251_AWA_1251.CHG_AC.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CHG_AC_M_INPUT;
            WS_FLD_NO = BPB1251_AWA_1251.CHG_AC_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPB1251_AWA_1251.CHG_AC);
        if (BPB1251_AWA_1251.CHG_AC.trim().length() > 0) {
            IBS.init(SCCGWA, CICACCU);
            CICACCU.DATA.AGR_NO = BPB1251_AWA_1251.CHG_AC;
            S000_CALL_CIZACCU();
            if (pgmRtn) return;
        }
        if (BPB1251_AWA_1251.STR_DT == 0) {
            BPB1251_AWA_1251.STR_DT = SCCGWA.COMM_AREA.AC_DATE;
        }
        if (BPB1251_AWA_1251.END_DT == 0) {
            BPB1251_AWA_1251.END_DT = SCCGWA.COMM_AREA.AC_DATE;
        }
        if (BPB1251_AWA_1251.STR_DT > BPB1251_AWA_1251.END_DT) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_EFFDT_GT_EXPDT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPB1251_AWA_1251.STR_DT);
        CEP.TRC(SCCGWA, BPB1251_AWA_1251.END_DT);
    }
    public void B020_MAIN_PROC() throws IOException,SQLException,Exception {
        B040_INTR_INFO();
        if (pgmRtn) return;
    }
    public void B040_INTR_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFEHIS);
        IBS.init(SCCGWA, BPCFEHIT);
        B050_DATA_TRANS();
        if (pgmRtn) return;
        if (BPB1251_AWA_1251.STR_POS == 0) {
            T000_STARTBR_AC();
            if (pgmRtn) return;
        } else {
            T000_STARTBR_AC_NEW();
            if (pgmRtn) return;
        }
        T000_READNEXT_BPTFEHIS();
        if (pgmRtn) return;
        WS_I = 1;
        while (WS_FRZ_FLG != 'N' 
            && SCCMPAG.FUNC != 'E' 
            && WS_I <= 8) {
            B060_01_DATA_TRANS_TO_FMT();
            if (pgmRtn) return;
            T000_READNEXT_BPTFEHIS();
            if (pgmRtn) return;
        }
        if (WS_FRZ_FLG == 'N') {
            BPCFEHIT.END_FLG = 'Y';
        }
        T000_ENDBR_BPTFEHIS();
        if (pgmRtn) return;
        B060_02_DATA_OUTPUT_FMT();
        if (pgmRtn) return;
    }
    public void B050_DATA_TRANS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFEHIS);
        BPRFEHIS.CHG_AC = BPB1251_AWA_1251.CHG_AC;
        BPRFEHIS.TX_TLR = BPB1251_AWA_1251.TX_TLR;
        BPRFEHIS.BSNS_NO = BPB1251_AWA_1251.BSNS_NO;
        WS_STR_DT = BPB1251_AWA_1251.STR_DT;
        WS_END_DT = BPB1251_AWA_1251.END_DT;
        JIBS_tmp_str[0] = "" + BPB1251_AWA_1251.STR_POS;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<24-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(0, 8).trim().length() == 0) BPRFEHIS.KEY.AC_DT = 0;
        else BPRFEHIS.KEY.AC_DT = Integer.parseInt(JIBS_tmp_str[0].substring(0, 8));
        JIBS_tmp_str[0] = "" + BPB1251_AWA_1251.STR_POS;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<24-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(9 - 1, 9 + 12 - 1).trim().length() == 0) BPRFEHIS.KEY.JRNNO = 0;
        else BPRFEHIS.KEY.JRNNO = Long.parseLong(JIBS_tmp_str[0].substring(9 - 1, 9 + 12 - 1));
        JIBS_tmp_str[0] = "" + BPB1251_AWA_1251.STR_POS;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<24-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(21 - 1, 21 + 4 - 1).trim().length() == 0) BPRFEHIS.KEY.JRN_SEQ = 0;
        else BPRFEHIS.KEY.JRN_SEQ = Short.parseShort(JIBS_tmp_str[0].substring(21 - 1, 21 + 4 - 1));
    }
    public void B060_02_DATA_OUTPUT_FMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCFEHIT;
        SCCFMT.DATA_LEN = 5673;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B060_01_DATA_TRANS_TO_FMT() throws IOException,SQLException,Exception {
        BPCFEHIT.TIMES[WS_I-1].AC_DT = BPRFEHIS.KEY.AC_DT;
        BPCFEHIT.TIMES[WS_I-1].JRNNO = BPRFEHIS.KEY.JRNNO;
        BPCFEHIT.TIMES[WS_I-1].JRN_SEQ = BPRFEHIS.KEY.JRN_SEQ;
        BPCFEHIT.TIMES[WS_I-1].FEE_CODE = BPRFEHIS.FEE_CODE;
        BPCFEHIT.TIMES[WS_I-1].TX_MMO = BPRFEHIS.TX_MMO;
        BPCFEHIT.TIMES[WS_I-1].CHG_AC = BPRFEHIS.CHG_AC;
        BPCFEHIT.TIMES[WS_I-1].CHG_AC_TY = BPRFEHIS.CHG_AC_TY;
        BPCFEHIT.TIMES[WS_I-1].CHG_FLG = BPRFEHIS.CHG_FLG;
        BPCFEHIT.TIMES[WS_I-1].CHG_BR = BPRFEHIS.CHG_BR;
        BPCFEHIT.TIMES[WS_I-1].FEE_CCY = BPRFEHIS.FEE_CCY;
        BPCFEHIT.TIMES[WS_I-1].FEE_BAS = BPRFEHIS.FEE_BAS;
        BPCFEHIT.TIMES[WS_I-1].FEE_FAV = BPRFEHIS.FEE_FAV;
        BPCFEHIT.TIMES[WS_I-1].FEE_AMT = BPRFEHIS.FEE_AMT;
        BPCFEHIT.TIMES[WS_I-1].CHG_CCY = BPRFEHIS.CHG_CCY;
        BPCFEHIT.TIMES[WS_I-1].CCY_TYPE = BPRFEHIS.CCY_TYPE;
        BPCFEHIT.TIMES[WS_I-1].CHG_BAS = BPRFEHIS.CHG_BAS;
        BPCFEHIT.TIMES[WS_I-1].CHG_FAV = BPRFEHIS.CHG_FAV;
        BPCFEHIT.TIMES[WS_I-1].CHG_AMT = BPRFEHIS.CHG_AMT;
        BPCFEHIT.TIMES[WS_I-1].ADJ_AMT = BPRFEHIS.ADJ_AMT;
        BPCFEHIT.TIMES[WS_I-1].DER_AMT = BPRFEHIS.DER_AMT;
        BPCFEHIT.TIMES[WS_I-1].RGN_NO = BPRFEHIS.RGN_NO;
        BPCFEHIT.TIMES[WS_I-1].REQFM_NO = BPRFEHIS.REQFM_NO;
        BPCFEHIT.TIMES[WS_I-1].CHNL_NO = BPRFEHIS.CHNL_NO;
        BPCFEHIT.TIMES[WS_I-1].FREE_FMT = BPRFEHIS.FREE_FMT;
        BPCFEHIT.TIMES[WS_I-1].CARD_PSBK_NO = BPRFEHIS.CARD_PSBK_NO;
        BPCFEHIT.TIMES[WS_I-1].SALE_DATE = BPRFEHIS.SALE_DATE;
        BPCFEHIT.TIMES[WS_I-1].DRCRFLG = BPRFEHIS.DRCRFLG;
        BPCFEHIT.TIMES[WS_I-1].TX_CCY = BPRFEHIS.TX_CCY;
        BPCFEHIT.TIMES[WS_I-1].TX_AC = BPRFEHIS.TX_AC;
        BPCFEHIT.TIMES[WS_I-1].REF_NO = BPRFEHIS.REF_NO;
        BPCFEHIT.TIMES[WS_I-1].TX_CI = BPRFEHIS.TX_CI;
        BPCFEHIT.TIMES[WS_I-1].TX_PROD = BPRFEHIS.TX_PROD;
        BPCFEHIT.TIMES[WS_I-1].TX_CD = BPRFEHIS.TX_CD;
        BPCFEHIT.TIMES[WS_I-1].FEE_CTRT = BPRFEHIS.FEE_CTRT;
        BPCFEHIT.TIMES[WS_I-1].FEE_CONT = BPRFEHIS.FEE_CONT;
        BPCFEHIT.TIMES[WS_I-1].FEE_ADVICE = BPRFEHIS.FEE_ADVICE;
        BPCFEHIT.TIMES[WS_I-1].FEE_CTRT_NO = BPRFEHIS.FEE_CTRT_NO;
        BPCFEHIT.TIMES[WS_I-1].BSNS_NO = BPRFEHIS.BSNS_NO;
        BPCFEHIT.TIMES[WS_I-1].CREV_NO = BPRFEHIS.CREV_NO;
        BPCFEHIT.TIMES[WS_I-1].TX_DT = BPRFEHIS.TX_DT;
        BPCFEHIT.TIMES[WS_I-1].TX_TM = BPRFEHIS.TX_TM;
        BPCFEHIT.TIMES[WS_I-1].TX_REV_DT = BPRFEHIS.TX_REV_DT;
        BPCFEHIT.TIMES[WS_I-1].TX_STS = BPRFEHIS.TX_STS;
        BPCFEHIT.TIMES[WS_I-1].BAT_OTRT_DT = BPRFEHIS.BAT_OTRT_DT;
        BPCFEHIT.TIMES[WS_I-1].BAT_OTRT_JRN = BPRFEHIS.BAT_OTRT_JRN;
        BPCFEHIT.TIMES[WS_I-1].BAT_OTRT_SEQ = BPRFEHIS.BAT_OTRT_SEQ;
        BPCFEHIT.TIMES[WS_I-1].PRINT_NUM = BPRFEHIS.PRINT_NUM;
        BPCFEHIT.TIMES[WS_I-1].REMARK = BPRFEHIS.REMARK;
        BPCFEHIT.TIMES[WS_I-1].TX_TLR = BPRFEHIS.TX_TLR;
        BPCFEHIT.TIMES[WS_I-1].CHECK_TLR = BPRFEHIS.CHECK_TLR;
        BPCFEHIT.TIMES[WS_I-1].SUP_TEL1 = BPRFEHIS.SUP_TEL1;
        BPCFEHIT.TIMES[WS_I-1].SUP_TEL2 = BPRFEHIS.SUP_TEL2;
        BPCFEHIT.TOD_REC_NUM = WS_I;
        WS_I = (short) (WS_I + 1);
        BPCFEHIT.TOTAL_NUM = (short) (BPCFEHIT.TOTAL_NUM + 1);
        JIBS_tmp_str[0] = "" + BPCFEHIT.END_POS;
        JIBS_f0 = "";
        for (int i=0;i<24-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
        JIBS_NumStr = JIBS_f0 + BPCFEHIT.END_POS;
        JIBS_tmp_str[1] = "" + BPRFEHIS.KEY.AC_DT;
        JIBS_tmp_int = JIBS_tmp_str[1].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
        JIBS_NumStr = JIBS_tmp_str[1] + JIBS_NumStr.substring(8);
        BPCFEHIT.END_POS = Long.parseLong(JIBS_NumStr);
        JIBS_tmp_str[0] = "" + BPCFEHIT.END_POS;
        JIBS_f0 = "";
        for (int i=0;i<24-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
        JIBS_NumStr = JIBS_f0 + BPCFEHIT.END_POS;
        JIBS_tmp_str[1] = "" + BPRFEHIS.KEY.JRNNO;
        JIBS_tmp_int = JIBS_tmp_str[1].length();
        for (int i=0;i<12-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
        JIBS_NumStr = JIBS_NumStr.substring(0, 9 - 1) + JIBS_tmp_str[1] + JIBS_NumStr.substring(9 + 12 - 1);
        BPCFEHIT.END_POS = Long.parseLong(JIBS_NumStr);
        JIBS_tmp_str[0] = "" + BPCFEHIT.END_POS;
        JIBS_f0 = "";
        for (int i=0;i<24-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
        JIBS_NumStr = JIBS_f0 + BPCFEHIT.END_POS;
        JIBS_tmp_str[1] = "" + BPRFEHIS.KEY.JRN_SEQ;
        JIBS_tmp_int = JIBS_tmp_str[1].length();
        for (int i=0;i<4-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
        JIBS_NumStr = JIBS_NumStr.substring(0, 21 - 1) + JIBS_tmp_str[1] + JIBS_NumStr.substring(21 + 4 - 1);
        BPCFEHIT.END_POS = Long.parseLong(JIBS_NumStr);
    }
    public void T000_STARTBR_AC() throws IOException,SQLException,Exception {
        BPTFEHIS_BR.rp = new DBParm();
        BPTFEHIS_BR.rp.TableName = "BPTFEHIS";
        BPTFEHIS_BR.rp.where = "( CHG_AC = :BPRFEHIS.CHG_AC ) "
            + "AND ( AC_DT >= :WS_STR_DT "
            + "AND AC_DT <= :WS_END_DT )";
        BPTFEHIS_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
        IBS.STARTBR(SCCGWA, BPRFEHIS, this, BPTFEHIS_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FRZ_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FRZ_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTFEHIS";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_AC_NEW() throws IOException,SQLException,Exception {
        BPTFEHIS_BR.rp = new DBParm();
        BPTFEHIS_BR.rp.TableName = "BPTFEHIS";
        BPTFEHIS_BR.rp.where = "CHG_AC = :BPRFEHIS.CHG_AC "
            + "AND ( ( AC_DT = :BPRFEHIS.KEY.AC_DT "
            + "AND JRNNO = :BPRFEHIS.KEY.JRNNO "
            + "AND JRN_SEQ > :BPRFEHIS.KEY.JRN_SEQ ) "
            + "OR ( AC_DT = :BPRFEHIS.KEY.AC_DT "
            + "AND JRNNO > :BPRFEHIS.KEY.JRNNO ) "
            + "OR ( AC_DT > :BPRFEHIS.KEY.AC_DT ) ) "
            + "AND AC_DT <= :WS_END_DT";
        BPTFEHIS_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
        IBS.STARTBR(SCCGWA, BPRFEHIS, this, BPTFEHIS_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FRZ_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FRZ_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTFEHIS";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READNEXT_BPTFEHIS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "NEXT BROWER");
        IBS.READNEXT(SCCGWA, BPRFEHIS, this, BPTFEHIS_BR);
        CEP.TRC(SCCGWA, BPRFEHIS.KEY.AC_DT);
        CEP.TRC(SCCGWA, BPRFEHIS.KEY.JRNNO);
        CEP.TRC(SCCGWA, BPRFEHIS.KEY.JRN_SEQ);
        CEP.TRC(SCCGWA, BPRFEHIS.FEE_CODE);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FRZ_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FRZ_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTFEHIS";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_BPTFEHIS() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTFEHIS_BR);
    }
    public void S000_CALL_DCZPACTY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = "DC-INQ-AC-INF";
        SCCCALL.COMMAREA_PTR = DCCPACTY;
        SCCCALL.ERRHDL_FLG = 'Y';
        IBS.CALL(SCCGWA, SCCCALL);
        CEP.TRC(SCCGWA, DCCPACTY.RC.RC_CODE);
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU         ", CICACCU);
        if (CICACCU.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICACCU.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
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
