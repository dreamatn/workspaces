package com.hisun.TD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class TDOT5761 {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    String WS_MSGID = " ";
    char WS_FUN_CI = ' ';
    char WS_FUN_CCY = ' ';
    char WS_FUN_TERM = ' ';
    short WS_ST = 0;
    short WS_EN = 0;
    String K_OUTPUT_FMT1 = "TD579";
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCBINF SCCBINF = new SCCBINF();
    TDCMACE TDCMACE = new TDCMACE();
    TDRCIIN TDRCIIN = new TDRCIIN();
    TDCLML TDCLML = new TDCLML();
    SCCGWA SCCGWA;
    TDB5761_AWA_5761 TDB5761_AWA_5761;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "TDOT5761 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "TDB5761_AWA_5761>");
        TDB5761_AWA_5761 = (TDB5761_AWA_5761) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        IBS.init(SCCGWA, TDCLML);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        S000_CALL_TDZLML();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDB5761_AWA_5761.PROD_CD);
        CEP.TRC(SCCGWA, TDB5761_AWA_5761.ACTI_NO);
        CEP.TRC(SCCGWA, TDB5761_AWA_5761.SHAR_FLG);
        CEP.TRC(SCCGWA, TDB5761_AWA_5761.LM_POINT);
        CEP.TRC(SCCGWA, TDB5761_AWA_5761.CHNL_NO);
        CEP.TRC(SCCGWA, TDB5761_AWA_5761.BR);
        if (TDB5761_AWA_5761.PROD_CD.trim().length() == 0) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_PROD_CD_IN_ERR);
        }
        if (TDB5761_AWA_5761.ACTI_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_ACTI_NO_IN_ERR);
        }
        if (TDB5761_AWA_5761.SHAR_FLG == ' ') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_SHAR_FLG_ERR);
        }
        CEP.TRC(SCCGWA, TDB5761_AWA_5761.LM_POINT);
        if (TDB5761_AWA_5761.LM_POINT.trim().length() == 0) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_LM_POINT_ERR);
        }
        if (TDB5761_AWA_5761.LM_POINT == null) TDB5761_AWA_5761.LM_POINT = "";
        JIBS_tmp_int = TDB5761_AWA_5761.LM_POINT.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) TDB5761_AWA_5761.LM_POINT += " ";
        if (TDB5761_AWA_5761.LM_POINT == null) TDB5761_AWA_5761.LM_POINT = "";
        JIBS_tmp_int = TDB5761_AWA_5761.LM_POINT.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) TDB5761_AWA_5761.LM_POINT += " ";
        if (TDB5761_AWA_5761.LM_POINT.substring(0, 1).equalsIgnoreCase("1") 
            && !TDB5761_AWA_5761.LM_POINT.substring(2 - 1, 2 + 2 - 1).equalsIgnoreCase("00")) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_LM_POINT_ERR);
        }
        if (TDB5761_AWA_5761.LM_POINT == null) TDB5761_AWA_5761.LM_POINT = "";
        JIBS_tmp_int = TDB5761_AWA_5761.LM_POINT.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) TDB5761_AWA_5761.LM_POINT += " ";
        if (TDB5761_AWA_5761.LM_POINT == null) TDB5761_AWA_5761.LM_POINT = "";
        JIBS_tmp_int = TDB5761_AWA_5761.LM_POINT.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) TDB5761_AWA_5761.LM_POINT += " ";
        if (TDB5761_AWA_5761.LM_POINT.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1") 
            && !TDB5761_AWA_5761.LM_POINT.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("0")) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_LM_POINT_ERR);
        }
        if (TDB5761_AWA_5761.LM_POINT == null) TDB5761_AWA_5761.LM_POINT = "";
        JIBS_tmp_int = TDB5761_AWA_5761.LM_POINT.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) TDB5761_AWA_5761.LM_POINT += " ";
        if (TDB5761_AWA_5761.LM_POINT.substring(0, 3).equalsIgnoreCase("000")) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_LM_POINT_ERR);
        }
        if (TDB5761_AWA_5761.LM_POINT == null) TDB5761_AWA_5761.LM_POINT = "";
        JIBS_tmp_int = TDB5761_AWA_5761.LM_POINT.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) TDB5761_AWA_5761.LM_POINT += " ";
        if (TDB5761_AWA_5761.LM_POINT == null) TDB5761_AWA_5761.LM_POINT = "";
        JIBS_tmp_int = TDB5761_AWA_5761.LM_POINT.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) TDB5761_AWA_5761.LM_POINT += " ";
        if (TDB5761_AWA_5761.LM_POINT.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1") 
            && TDB5761_AWA_5761.LM_POINT.substring(0, 3).equalsIgnoreCase("000")) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_LM_POINT_ERR);
        }
        if (TDB5761_AWA_5761.LM_POINT == null) TDB5761_AWA_5761.LM_POINT = "";
        JIBS_tmp_int = TDB5761_AWA_5761.LM_POINT.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) TDB5761_AWA_5761.LM_POINT += " ";
        if (TDB5761_AWA_5761.LM_POINT.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1") 
            && (TDB5761_AWA_5761.CHNL_NO.trim().length() == 0 
            || TDB5761_AWA_5761.CHNL_NO.equalsIgnoreCase("99999"))) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_LM_POINT_ERR);
        }
        if (TDB5761_AWA_5761.BR == 0) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_BR_INPUT_ERR);
        }
        if (TDB5761_AWA_5761.LM_POINT == null) TDB5761_AWA_5761.LM_POINT = "";
        JIBS_tmp_int = TDB5761_AWA_5761.LM_POINT.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) TDB5761_AWA_5761.LM_POINT += " ";
        if (!TDB5761_AWA_5761.LM_POINT.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1") 
            && (TDB5761_AWA_5761.CHNL_NO.trim().length() > 0 
            && !TDB5761_AWA_5761.CHNL_NO.equalsIgnoreCase("99999"))) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_ACTI_NOT_CHNL);
        }
    }
    public void S000_CALL_TDZLML() throws IOException,SQLException,Exception {
        TDCLML.FUNC = TDB5761_AWA_5761.FUNC;
        TDCLML.PROD_CD = TDB5761_AWA_5761.PROD_CD;
        TDCLML.ACTI_NO = TDB5761_AWA_5761.ACTI_NO;
        TDCLML.SHAR_FLG = TDB5761_AWA_5761.SHAR_FLG;
        TDCLML.LM_POINT = TDB5761_AWA_5761.LM_POINT;
        TDCLML.BR = TDB5761_AWA_5761.BR;
        TDCLML.CHNL_NO = TDB5761_AWA_5761.CHNL_NO;
        TDCLML.BAL = TDB5761_AWA_5761.BAL;
        IBS.CALLCPN(SCCGWA, "TD-Z-LML", TDCLML);
        if (TDCLML.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCLML.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSGID, SCCBINF);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
