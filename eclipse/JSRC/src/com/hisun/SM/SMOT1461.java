package com.hisun.SM;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class SMOT1461 {
    String JIBS_tmp_str[] = new String[10];
    SMOT1461_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new SMOT1461_WS_TEMP_VARIABLE();
    SMCMSG_ERROR_MSG SMCMSG_ERROR_MSG = new SMCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SMCSUWF SMCSUWF = new SMCSUWF();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCGWA SCCGWA;
    SMB1450_AWA_1450 SMB1450_AWA_1450;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "SMOT1461 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "SMB1450_AWA_1450>");
        SMB1450_AWA_1450 = (SMB1450_AWA_1450) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, WS_TEMP_VARIABLE);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B001_CHECK_INPUT();
        B005_ADD_RECORD();
    }
    public void B001_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B005_ADD_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SMCSUWF);
        SMCSUWF.CODE = SMB1450_AWA_1450.CODE;
        SMCSUWF.NAME = SMB1450_AWA_1450.NAME;
        SMCSUWF.TYPE = SMB1450_AWA_1450.TYPE;
        SMCSUWF.FIRST_Q = SMB1450_AWA_1450.FIRST_Q;
        SMCSUWF.CMP_NAME = SMB1450_AWA_1450.CMPNAME;
        if (SMB1450_AWA_1450.DAYS.trim().length() == 0) SMCSUWF.DAYS = 0;
        else SMCSUWF.DAYS = Short.parseShort(SMB1450_AWA_1450.DAYS);
        SMCSUWF.DEL_FLG = SMB1450_AWA_1450.DEL_FLG;
        SMCSUWF.TLT_FLG = SMB1450_AWA_1450.TLT_FLG;
        SMCSUWF.TR_CODE = SMB1450_AWA_1450.DATA[1-1].TRCODE;
        SMCSUWF.UPT_DATE = SCCGWA.COMM_AREA.TR_DATE;
        SMCSUWF.UPT_TLT = SCCGWA.COMM_AREA.TL_ID;
        S000_CALL_SMZSUWF();
    }
    public void S000_CALL_SMZSUWF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "SM-S-UPD-WF", SMCSUWF);
        if (SMCSUWF.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SMCSUWF.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_TEMP_VARIABLE.WS_MSGID);
            WS_TEMP_VARIABLE.WS_FLD_NO = SMB1450_AWA_1450.CODE_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_TEMP_VARIABLE.WS_MSGID);
        CEP.ERR(SCCGWA, JIBS_tmp_str[0], WS_TEMP_VARIABLE.WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_TEMP_VARIABLE.WS_MSGID);
        CEP.ERRC(SCCGWA, JIBS_tmp_str[0], WS_TEMP_VARIABLE.WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
