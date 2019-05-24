package com.hisun.SM;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class SMOT2012 {
    String JIBS_tmp_str[] = new String[10];
    SMOT2012_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new SMOT2012_WS_TEMP_VARIABLE();
    SMCMSG_ERROR_MSG SMCMSG_ERROR_MSG = new SMCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCBSP SCCBSP = new SCCBSP();
    SCCRWSPC SCCRWSPC = new SCCRWSPC();
    SCRBSPC SCRBSPC = new SCRBSPC();
    SCCRWBTL SCCRWBTL = new SCCRWBTL();
    SCRBBTL SCRBBTL = new SCRBBTL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCGWA SCCGWA;
    SMB2010_AWA_2010 SMB2010_AWA_2010;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "SMOT2012 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "SMB2010_AWA_2010>");
        SMB2010_AWA_2010 = (SMB2010_AWA_2010) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, WS_TEMP_VARIABLE);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B001_CHECK_INPUT();
        B002_MAIL_PROCESS();
    }
    public void B001_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SMB2010_AWA_2010.SERVCODE);
        CEP.TRC(SCCGWA, SMB2010_AWA_2010.AP_TYPE);
        CEP.TRC(SCCGWA, SMB2010_AWA_2010.AP_TYPE);
        IBS.init(SCCGWA, SCCRWSPC);
        SCCRWSPC.PTR = SCRBSPC;
        SCCRWSPC.LEN = 382;
        SCRBSPC.KEY.SERV_CODE = SMB2010_AWA_2010.SERVCODE;
        SCCRWSPC.FUNC = 'Q';
        S000_CALL_SCZRWSPC();
        if (SCRBSPC.BSP_TYPE == '1') {
            IBS.CPY2CLS(SCCGWA, SMCMSG_ERROR_MSG.SM_BAH_BSP_ERR, WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
        }
    }
    public void B002_MAIL_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCRWBTL);
        SCCRWBTL.LEN = 837;
        SCCRWBTL.PTR = SCRBBTL;
        SCRBBTL.KEY.SERV_CODE = SMB2010_AWA_2010.SERVCODE;
        SCRBBTL.KEY.AP_TYPE = SMB2010_AWA_2010.AP_TYPE;
        SCRBBTL.KEY.AP_BATNO = SMB2010_AWA_2010.BAT_SEQ;
        SCCRWBTL.FUNC = 'Q';
        S000_CALL_SCZRWBTL();
        if (SCRBBTL.REDEFINES28.REDEFINES30.STEP_CTL[3-1].STEP_STS == 'F' 
            || (SCRBBTL.REDEFINES28.REDEFINES30.STEP_CTL[3-1].STEP_PROC_NAME.trim().length() == 0 
            && SCRBBTL.REDEFINES28.REDEFINES30.STEP_CTL[2-1].STEP_STS == 'F')) {
            IBS.CPY2CLS(SCCGWA, SMCMSG_ERROR_MSG.SM_BSP_NOT_ERR, WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
        }
        IBS.init(SCCGWA, SCCBSP);
        SCCBSP.SERV_CODE = SMB2010_AWA_2010.SERVCODE;
        SCCBSP.BH_SEQ.BH_TYPE = SMB2010_AWA_2010.AP_TYPE;
        SCCBSP.BH_SEQ.BH_BATNO = SMB2010_AWA_2010.BAT_SEQ;
        SCCBSP.PARM_DA1 = SCRBBTL.PARM_DA1;
        SCCBSP.PARM_DA2 = SCRBBTL.PARM_DA2;
        SCCBSP.PARM_DA3 = SCRBBTL.PARM_DA3;
        SCCBSP.PARM_DA4 = SCRBBTL.PARM_DA4;
        SCCBSP.PARM_DA5 = SCRBBTL.PARM_DA5;
        S000_CALL_SCZOBSP();
    }
    public void S000_CALL_SCZOBSP() throws IOException,SQLException,Exception {
        SCZOBSP SCZOBSP = new SCZOBSP();
        SCZOBSP.MP(SCCGWA, SCCBSP);
    }
    public void S000_CALL_SCZRWSPC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = "SC-R-UPD-BSP-CTL";
        SCCCALL.COMMAREA_PTR = SCCRWSPC;
        SCCCALL.CONTINUE_FLG = 'Y';
        IBS.CALL(SCCGWA, SCCCALL);
        if (SCCRWSPC.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCRWSPC.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_SCZRWBTL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "SC-R-MAINTAIN-BBTL", SCCRWBTL);
        if (SCCRWBTL.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCRWBTL.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_TEMP_VARIABLE.WS_MSGID);
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
