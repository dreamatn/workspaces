package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT1334 {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    BPOT1334_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new BPOT1334_WS_TEMP_VARIABLE();
    BPOT1334_WS_OUT_PUT_DATA WS_OUT_PUT_DATA = new BPOT1334_WS_OUT_PUT_DATA();
    short WS_INT = 0;
    int WS_CODE_INT = 0;
    long WS_SEQ_NO_TMP = 0;
    BPOT1334_WS_COND_FLG WS_COND_FLG = new BPOT1334_WS_COND_FLG();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    BPCSHSEQ BPCSHSEQ = new BPCSHSEQ();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMPAG SCCMPAG = new SCCMPAG();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    SCCGWA SCCGWA;
    BPB1310_AWA_1310 BPB1310_AWA_1310;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPOT1334 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB1310_AWA_1310>");
        BPB1310_AWA_1310 = (BPB1310_AWA_1310) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, WS_COND_FLG);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "==========");
        CEP.TRC(SCCGWA, BPB1310_AWA_1310.TYPE);
        CEP.TRC(SCCGWA, BPB1310_AWA_1310.TYP_DESC);
        CEP.TRC(SCCGWA, BPB1310_AWA_1310.CODE);
        CEP.TRC(SCCGWA, BPB1310_AWA_1310.CD_DESC);
        CEP.TRC(SCCGWA, BPB1310_AWA_1310.SEQ_DESC);
        CEP.TRC(SCCGWA, BPB1310_AWA_1310.SEQ_NO);
        CEP.TRC(SCCGWA, BPB1310_AWA_1310.NUM);
        CEP.TRC(SCCGWA, BPB1310_AWA_1310.AC_OFC);
        CEP.TRC(SCCGWA, BPB1310_AWA_1310.REMARK);
        B001_CHECK_INPUT();
        if (pgmRtn) return;
        B002_DEAL_PROC();
        if (pgmRtn) return;
        B003_OUTPUT_PROC();
        if (pgmRtn) return;
    }
    public void B001_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPB1310_AWA_1310.SEQ_NO == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_HSEQ_SEQ_MUST_INPUT, WS_TEMP_VARIABLE.WS_MSGID);
            WS_TEMP_VARIABLE.WS_FLD_NO = BPB1310_AWA_1310.SEQ_NO_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPB1310_AWA_1310.SEQ_NO > 9999999999) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_SEQ_CANOT_LOOGER_10, WS_TEMP_VARIABLE.WS_MSGID);
            WS_TEMP_VARIABLE.WS_FLD_NO = BPB1310_AWA_1310.SEQ_NO_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPB1310_AWA_1310.NUM > 20) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NUM_CANOT_LAR_20, WS_TEMP_VARIABLE.WS_MSGID);
            WS_TEMP_VARIABLE.WS_FLD_NO = BPB1310_AWA_1310.NUM_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPB1310_AWA_1310.CODE == null) BPB1310_AWA_1310.CODE = "";
        JIBS_tmp_int = BPB1310_AWA_1310.CODE.length();
        for (int i=0;i<10-JIBS_tmp_int;i++) BPB1310_AWA_1310.CODE += " ";
        if (BPB1310_AWA_1310.CODE.substring(3 - 1, 3 + 1 - 1).trim().length() > 0) {
            if (BPB1310_AWA_1310.CODE == null) BPB1310_AWA_1310.CODE = "";
            JIBS_tmp_int = BPB1310_AWA_1310.CODE.length();
            for (int i=0;i<10-JIBS_tmp_int;i++) BPB1310_AWA_1310.CODE += " ";
            if (!IBS.isNumeric(BPB1310_AWA_1310.CODE.substring(0, 3))) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CODE_MUST_NUMERIC, WS_TEMP_VARIABLE.WS_MSGID);
                WS_TEMP_VARIABLE.WS_FLD_NO = BPB1310_AWA_1310.NUM_NO;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        } else {
            if (BPB1310_AWA_1310.CODE == null) BPB1310_AWA_1310.CODE = "";
            JIBS_tmp_int = BPB1310_AWA_1310.CODE.length();
            for (int i=0;i<10-JIBS_tmp_int;i++) BPB1310_AWA_1310.CODE += " ";
            if (!IBS.isNumeric(BPB1310_AWA_1310.CODE.substring(0, 2))) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CODE_MUST_NUMERIC, WS_TEMP_VARIABLE.WS_MSGID);
                WS_TEMP_VARIABLE.WS_FLD_NO = BPB1310_AWA_1310.NUM_NO;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B002_DEAL_PROC() throws IOException,SQLException,Exception {
        WS_OUT_PUT_DATA.WS_TYPE = BPB1310_AWA_1310.TYPE;
        WS_OUT_PUT_DATA.WS_TYPE_DESC = BPB1310_AWA_1310.TYP_DESC;
        WS_OUT_PUT_DATA.WS_CODE = BPB1310_AWA_1310.CODE;
        WS_OUT_PUT_DATA.WS_CODE_DESC = BPB1310_AWA_1310.CD_DESC;
        WS_OUT_PUT_DATA.WS_SEQ_DESC = BPB1310_AWA_1310.SEQ_DESC;
        WS_OUT_PUT_DATA.WS_SEQ_NO = BPB1310_AWA_1310.SEQ_NO;
        if (BPB1310_AWA_1310.NUM != 0) {
            WS_OUT_PUT_DATA.WS_NO_OF_ACCOUNT = BPB1310_AWA_1310.NUM;
        } else {
            WS_OUT_PUT_DATA.WS_NO_OF_ACCOUNT = 1;
        }
        WS_OUT_PUT_DATA.WS_AC_OFFICER = BPB1310_AWA_1310.AC_OFC;
        WS_OUT_PUT_DATA.WS_REMARK = BPB1310_AWA_1310.REMARK;
        CEP.TRC(SCCGWA, WS_OUT_PUT_DATA.WS_TYPE);
        CEP.TRC(SCCGWA, WS_OUT_PUT_DATA.WS_TYPE_DESC);
        CEP.TRC(SCCGWA, WS_OUT_PUT_DATA.WS_CODE);
        CEP.TRC(SCCGWA, WS_OUT_PUT_DATA.WS_CODE_DESC);
        CEP.TRC(SCCGWA, WS_OUT_PUT_DATA.WS_SEQ_DESC);
        CEP.TRC(SCCGWA, WS_OUT_PUT_DATA.WS_SEQ_NO);
        CEP.TRC(SCCGWA, WS_OUT_PUT_DATA.WS_NO_OF_ACCOUNT);
        CEP.TRC(SCCGWA, WS_OUT_PUT_DATA.WS_AC_OFFICER);
        CEP.TRC(SCCGWA, WS_OUT_PUT_DATA.WS_REMARK);
        if (BPB1310_AWA_1310.CODE.trim().length() == 0) WS_CODE_INT = 0;
        else WS_CODE_INT = Integer.parseInt(BPB1310_AWA_1310.CODE);
        CEP.TRC(SCCGWA, BPB1310_AWA_1310.CODE);
        CEP.TRC(SCCGWA, WS_CODE_INT);
        if (WS_CODE_INT > 99) {
            WS_COND_FLG.WS_CTL_LEN = '3';
        } else {
            WS_COND_FLG.WS_CTL_LEN = '2';
        }
        WS_SEQ_NO_TMP = BPB1310_AWA_1310.SEQ_NO;
        for (WS_INT = 1; WS_INT <= WS_OUT_PUT_DATA.WS_NO_OF_ACCOUNT; WS_INT += 1) {
            if (WS_COND_FLG.WS_CTL_LEN == '3') {
                if (WS_OUT_PUT_DATA.WS_CODE == null) WS_OUT_PUT_DATA.WS_CODE = "";
                JIBS_tmp_int = WS_OUT_PUT_DATA.WS_CODE.length();
                for (int i=0;i<10-JIBS_tmp_int;i++) WS_OUT_PUT_DATA.WS_CODE += " ";
                WS_OUT_PUT_DATA.WS_EVERY_AC_INFO[WS_INT-1].WS_AC_NO = WS_OUT_PUT_DATA.WS_CODE.substring(0, 3);
                if (WS_OUT_PUT_DATA.WS_EVERY_AC_INFO[WS_INT-1].WS_AC_NO == null) WS_OUT_PUT_DATA.WS_EVERY_AC_INFO[WS_INT-1].WS_AC_NO = "";
                JIBS_tmp_int = WS_OUT_PUT_DATA.WS_EVERY_AC_INFO[WS_INT-1].WS_AC_NO.length();
                for (int i=0;i<32-JIBS_tmp_int;i++) WS_OUT_PUT_DATA.WS_EVERY_AC_INFO[WS_INT-1].WS_AC_NO += " ";
                WS_OUT_PUT_DATA.WS_EVERY_AC_INFO[WS_INT-1].WS_AC_NO = WS_OUT_PUT_DATA.WS_CODE.substring(0, 3) + WS_OUT_PUT_DATA.WS_EVERY_AC_INFO[WS_INT-1].WS_AC_NO.substring(3);
                JIBS_tmp_str[0] = "" + WS_SEQ_NO_TMP;
                JIBS_tmp_int = JIBS_tmp_str[0].length();
                for (int i=0;i<15-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                WS_OUT_PUT_DATA.WS_EVERY_AC_INFO[WS_INT-1].WS_AC_NO = JIBS_tmp_str[0].substring(7 - 1, 7 + 9 - 1);
                if (WS_OUT_PUT_DATA.WS_EVERY_AC_INFO[WS_INT-1].WS_AC_NO == null) WS_OUT_PUT_DATA.WS_EVERY_AC_INFO[WS_INT-1].WS_AC_NO = "";
                JIBS_tmp_int = WS_OUT_PUT_DATA.WS_EVERY_AC_INFO[WS_INT-1].WS_AC_NO.length();
                for (int i=0;i<32-JIBS_tmp_int;i++) WS_OUT_PUT_DATA.WS_EVERY_AC_INFO[WS_INT-1].WS_AC_NO += " ";
                WS_OUT_PUT_DATA.WS_EVERY_AC_INFO[WS_INT-1].WS_AC_NO = WS_OUT_PUT_DATA.WS_EVERY_AC_INFO[WS_INT-1].WS_AC_NO.substring(0, 4 - 1) + JIBS_tmp_str[0].substring(7 - 1, 7 + 9 - 1) + WS_OUT_PUT_DATA.WS_EVERY_AC_INFO[WS_INT-1].WS_AC_NO.substring(4 + 9 - 1);
            } else {
                if (WS_OUT_PUT_DATA.WS_CODE == null) WS_OUT_PUT_DATA.WS_CODE = "";
                JIBS_tmp_int = WS_OUT_PUT_DATA.WS_CODE.length();
                for (int i=0;i<10-JIBS_tmp_int;i++) WS_OUT_PUT_DATA.WS_CODE += " ";
                WS_OUT_PUT_DATA.WS_EVERY_AC_INFO[WS_INT-1].WS_AC_NO = WS_OUT_PUT_DATA.WS_CODE.substring(0, 2);
                if (WS_OUT_PUT_DATA.WS_EVERY_AC_INFO[WS_INT-1].WS_AC_NO == null) WS_OUT_PUT_DATA.WS_EVERY_AC_INFO[WS_INT-1].WS_AC_NO = "";
                JIBS_tmp_int = WS_OUT_PUT_DATA.WS_EVERY_AC_INFO[WS_INT-1].WS_AC_NO.length();
                for (int i=0;i<32-JIBS_tmp_int;i++) WS_OUT_PUT_DATA.WS_EVERY_AC_INFO[WS_INT-1].WS_AC_NO += " ";
                WS_OUT_PUT_DATA.WS_EVERY_AC_INFO[WS_INT-1].WS_AC_NO = WS_OUT_PUT_DATA.WS_CODE.substring(0, 2) + WS_OUT_PUT_DATA.WS_EVERY_AC_INFO[WS_INT-1].WS_AC_NO.substring(2);
                JIBS_tmp_str[0] = "" + WS_SEQ_NO_TMP;
                JIBS_tmp_int = JIBS_tmp_str[0].length();
                for (int i=0;i<15-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                WS_OUT_PUT_DATA.WS_EVERY_AC_INFO[WS_INT-1].WS_AC_NO = JIBS_tmp_str[0].substring(6 - 1, 6 + 10 - 1);
                if (WS_OUT_PUT_DATA.WS_EVERY_AC_INFO[WS_INT-1].WS_AC_NO == null) WS_OUT_PUT_DATA.WS_EVERY_AC_INFO[WS_INT-1].WS_AC_NO = "";
                JIBS_tmp_int = WS_OUT_PUT_DATA.WS_EVERY_AC_INFO[WS_INT-1].WS_AC_NO.length();
                for (int i=0;i<32-JIBS_tmp_int;i++) WS_OUT_PUT_DATA.WS_EVERY_AC_INFO[WS_INT-1].WS_AC_NO += " ";
                WS_OUT_PUT_DATA.WS_EVERY_AC_INFO[WS_INT-1].WS_AC_NO = WS_OUT_PUT_DATA.WS_EVERY_AC_INFO[WS_INT-1].WS_AC_NO.substring(0, 3 - 1) + JIBS_tmp_str[0].substring(6 - 1, 6 + 10 - 1) + WS_OUT_PUT_DATA.WS_EVERY_AC_INFO[WS_INT-1].WS_AC_NO.substring(3 + 10 - 1);
            }
            WS_OUT_PUT_DATA.WS_EVERY_AC_INFO[WS_INT-1].WS_OBL_FLG = 'Y';
            WS_SEQ_NO_TMP = WS_SEQ_NO_TMP + 1;
            CEP.TRC(SCCGWA, WS_OUT_PUT_DATA.WS_EVERY_AC_INFO[WS_INT-1].WS_AC_NO);
        }
    }
    public void B003_OUTPUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 1181;
        SCCMPAG.SCR_ROW_CNT = 50;
        SCCMPAG.SCR_COL_CNT = 100;
        B_MPAG();
        if (pgmRtn) return;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_OUT_PUT_DATA);
        SCCMPAG.DATA_LEN = 1181;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZPQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-PRDT-INFO", BPCPQPRD);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_TEMP_VARIABLE.WS_MSGID);
        CEP.ERR(SCCGWA, JIBS_tmp_str[0], WS_TEMP_VARIABLE.WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_TEMP_VARIABLE.WS_MSGID);
        CEP.ERRC(SCCGWA, JIBS_tmp_str[0], WS_TEMP_VARIABLE.WS_FLD_NO);
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
