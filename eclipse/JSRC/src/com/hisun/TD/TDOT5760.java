package com.hisun.TD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class TDOT5760 {
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
    TDCZML TDCZML = new TDCZML();
    SCCGWA SCCGWA;
    TDB5760_AWA_5760 TDB5760_AWA_5760;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "TDOT5760 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "TDB5760_AWA_5760>");
        TDB5760_AWA_5760 = (TDB5760_AWA_5760) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        IBS.init(SCCGWA, TDCLML);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (TDB5760_AWA_5760.FUNC == 'I' 
            || TDB5760_AWA_5760.FUNC == 'E') {
            S000_CALL_TDZLML();
        } else {
            S000_CALL_TDZZML();
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDB5760_AWA_5760.PROD_CD);
        CEP.TRC(SCCGWA, TDB5760_AWA_5760.ACTI_NO);
        CEP.TRC(SCCGWA, TDB5760_AWA_5760.SDT);
        CEP.TRC(SCCGWA, TDB5760_AWA_5760.DDT);
        if (TDB5760_AWA_5760.PROD_CD.trim().length() == 0) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_PROD_CD_IN_ERR);
        }
        if (TDB5760_AWA_5760.ACTI_NO.trim().length() == 0) {
            if (TDB5760_AWA_5760.SDT == 0 
                && TDB5760_AWA_5760.DDT == 0) {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_DT_SPACE_ERR);
            }
        }
    }
    public void S000_CALL_TDZLML() throws IOException,SQLException,Exception {
        TDCLML.FUNC = TDB5760_AWA_5760.FUNC;
        TDCLML.PROD_CD = TDB5760_AWA_5760.PROD_CD;
        TDCLML.ACTI_NO = TDB5760_AWA_5760.ACTI_NO;
        TDCLML.SDT = TDB5760_AWA_5760.SDT;
        TDCLML.DDT = TDB5760_AWA_5760.DDT;
        TDCLML.SMK = TDB5760_AWA_5760.SMK;
        TDCLML.ACTI_FLG = '1';
        IBS.CALLCPN(SCCGWA, "TD-Z-LML", TDCLML);
        if (TDCLML.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCLML.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_TDZZML() throws IOException,SQLException,Exception {
        TDCZML.FUNC = TDB5760_AWA_5760.FUNC;
        TDCZML.PROD_CD = TDB5760_AWA_5760.PROD_CD;
        TDCZML.ACTI_NO = TDB5760_AWA_5760.ACTI_NO;
        TDCZML.SDT = TDB5760_AWA_5760.SDT;
        TDCZML.DDT = TDB5760_AWA_5760.DDT;
        TDCZML.SMK = TDB5760_AWA_5760.SMK;
        TDCZML.ACTI_FLG = '1';
        IBS.CALLCPN(SCCGWA, "TD-Z-ZML", TDCZML);
        if (TDCLML.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCZML.RC);
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
