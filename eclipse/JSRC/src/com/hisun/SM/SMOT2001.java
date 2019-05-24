package com.hisun.SM;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class SMOT2001 {
    String JIBS_tmp_str[] = new String[10];
    SMOT2001_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new SMOT2001_WS_TEMP_VARIABLE();
    SMCMSG_ERROR_MSG SMCMSG_ERROR_MSG = new SMCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SMCSQBSP SMCSQBSP = new SMCSQBSP();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCGWA SCCGWA;
    SMB2001_AWA_2001 SMB2001_AWA_2001;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "SMOT2001 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "SMB2001_AWA_2001>");
        SMB2001_AWA_2001 = (SMB2001_AWA_2001) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
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
        IBS.init(SCCGWA, SMCSQBSP);
        SMCSQBSP.SERV_CODE = SMB2001_AWA_2001.SERVCODE;
        SMCSQBSP.REMARK = SMB2001_AWA_2001.REMARK;
        SMCSQBSP.TABLE_NAME = SMB2001_AWA_2001.TB_NAME;
        SMCSQBSP.UCMP_NAME = SMB2001_AWA_2001.UCMP_NM;
        SMCSQBSP.BSP_TYPE = SMB2001_AWA_2001.BSP_TYPE;
        SMCSQBSP.OPER_STS = SMB2001_AWA_2001.OPER_STS;
        SMCSQBSP.WAIT_SERV_CODE = SMB2001_AWA_2001.WSVCODE;
        SMCSQBSP.REVERSE_FLG = SMB2001_AWA_2001.RV_FLG;
        SMCSQBSP.ERROR_FLG = SMB2001_AWA_2001.ERR_FLG;
        SMCSQBSP.RUN_TYPE = SMB2001_AWA_2001.RUN_TYPE;
        SMCSQBSP.TR_CODE = SMB2001_AWA_2001.TR_CODE;
        SMCSQBSP.CMP_NAME = SMB2001_AWA_2001.CMP_NAME;
        SMCSQBSP.RCP_NAME = SMB2001_AWA_2001.RCP_NAME;
        SMCSQBSP.GEN_PRO = SMB2001_AWA_2001.GEN_PRO;
        SMCSQBSP.BSP_PROC = SMB2001_AWA_2001.BSP_PROC;
        SMCSQBSP.BAT_PROC = SMB2001_AWA_2001.BAT_PROC;
        SMCSQBSP.CMT_SEQ = SMB2001_AWA_2001.CMT_SEQ;
        S000_CALL_SMZSABSP();
    }
    public void S000_CALL_SMZSABSP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "SM-S-ADD-BSP-CTL", SMCSQBSP);
        if (SMCSQBSP.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SMCSQBSP.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_TEMP_VARIABLE.WS_MSGID);
            WS_TEMP_VARIABLE.WS_FLD_NO = SMB2001_AWA_2001.SERVCODE_NO;
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
