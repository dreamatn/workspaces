package com.hisun.SM;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class SMOT1468 {
    String JIBS_tmp_str[] = new String[10];
    SMOT1468_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new SMOT1468_WS_TEMP_VARIABLE();
    SMCMSG_ERROR_MSG SMCMSG_ERROR_MSG = new SMCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SMCSUWFB SMCSUWFB = new SMCSUWFB();
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
        CEP.TRC(SCCGWA, "SMOT1468 return!");
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
        if (SMB1450_AWA_1450.SEQ != 0) {
            for (WS_TEMP_VARIABLE.WS_POS = 1; WS_TEMP_VARIABLE.WS_POS <= SMB1450_AWA_1450.SEQ; WS_TEMP_VARIABLE.WS_POS += 1) {
                if (SMB1450_AWA_1450.LVL_DATA[WS_TEMP_VARIABLE.WS_POS-1].LVL == ' ') {
                    IBS.CPY2CLS(SCCGWA, SMCMSG_ERROR_MSG.SM_FIELD_MUSTINPUT, WS_TEMP_VARIABLE.WS_MSGID);
                    WS_TEMP_VARIABLE.WS_FLD_NO = SMB1450_AWA_1450.LVL_DATA[WS_TEMP_VARIABLE.WS_POS-1].LVL_NO;
                    S000_ERR_MSG_PROC();
                }
            }
        }
    }
    public void B005_ADD_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SMCSUWFB);
        SMCSUWFB.TR_CODE = SMB1450_AWA_1450.DATA[1-1].TRCODE;
        SMCSUWFB.WF_CODE = SMB1450_AWA_1450.CODE;
        SMCSUWFB.SEQ = SMB1450_AWA_1450.SEQ;
        SMCSUWFB.UPD_FLG = SMB1450_AWA_1450.UPD_FLG;
        SMCSUWFB.RELAT_TYPE = SMB1450_AWA_1450.REL_TYPE;
        SMCSUWFB.OW_FLG = SMB1450_AWA_1450.DATA[1-1].OW_FLG;
        SMCSUWFB.DEL_FLG = SMB1450_AWA_1450.DEL_FLG;
        SMCSUWFB.CR_BK_FLG = SMB1450_AWA_1450.CRBK_FLG;
        for (WS_TEMP_VARIABLE.WS_POS = 1; WS_TEMP_VARIABLE.WS_POS <= 9; WS_TEMP_VARIABLE.WS_POS += 1) {
            SMCSUWFB.REDEFINES8.DATA_LIST[WS_TEMP_VARIABLE.WS_POS-1].LVL = SMB1450_AWA_1450.LVL_DATA[WS_TEMP_VARIABLE.WS_POS-1].LVL;
            SMCSUWFB.LVL_DATA = IBS.CLS2CPY(SCCGWA, SMCSUWFB.REDEFINES8);
            SMCSUWFB.REDEFINES8.DATA_LIST[WS_TEMP_VARIABLE.WS_POS-1].BR = SMB1450_AWA_1450.LVL_DATA[WS_TEMP_VARIABLE.WS_POS-1].BR;
            SMCSUWFB.LVL_DATA = IBS.CLS2CPY(SCCGWA, SMCSUWFB.REDEFINES8);
        }
        S000_CALL_SMZSUWFB();
    }
    public void S000_CALL_SMZSUWFB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "SM-S-UPD-WFB", SMCSUWFB);
        if (SMCSUWFB.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SMCSUWFB.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_TEMP_VARIABLE.WS_MSGID);
            WS_TEMP_VARIABLE.WS_FLD_NO = SMB1450_AWA_1450.DATA[1-1].TRCODE_NO;
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
