package com.hisun.SM;

import com.hisun.BP.*;
import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class SMOT1803 {
    String JIBS_tmp_str[] = new String[10];
    String K_OUTPUT_FMT = "BPP43";
    String CPN_DDIC_MAINTAIN = "SM-S_DDIC_MAINTAIN  ";
    SMOT1803_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new SMOT1803_WS_TEMP_VARIABLE();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    BPCXP43 BPCXP43 = new BPCXP43();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SMCODICM SMCODICM = new SMCODICM();
    SCCGWA SCCGWA;
    SMB1800_AWA_1800 SMB1800_AWA_1800;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "SMOT1803 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "SMB1800_AWA_1800>");
        SMB1800_AWA_1800 = (SMB1800_AWA_1800) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, WS_TEMP_VARIABLE);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        B200_INQUIRE_PROC();
        B300_TRANS_DATA();
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B200_INQUIRE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SMCODICM);
        SMCODICM.FUNC = 'I';
        SMCODICM.NAME = SMB1800_AWA_1800.FLD_DNAM;
        CEP.TRC(SCCGWA, SMB1800_AWA_1800.FLD_DNAM);
        S000_CALL_SMZDDICM();
    }
    public void S000_CALL_SMZDDICM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_DDIC_MAINTAIN, SMCODICM, true);
        if (SMCODICM.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SMCODICM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
        }
    }
    public void B300_TRANS_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCXP43);
        IBS.init(SCCGWA, SCCFMT);
        if (SMCODICM.TYPE == '0') {
            WS_TEMP_VARIABLE.WS_FLD.WS_FLD_TYPE = '9';
        } else {
            if (SMCODICM.TYPE == '3') {
                WS_TEMP_VARIABLE.WS_FLD.WS_FLD_TYPE = 'M';
            } else {
                if (SMCODICM.TYPE == '2') {
                    WS_TEMP_VARIABLE.WS_FLD.WS_FLD_TYPE = 'C';
                } else {
                    WS_TEMP_VARIABLE.WS_FLD.WS_FLD_TYPE = 'X';
                }
            }
        }
        WS_TEMP_VARIABLE.WS_FLD.WS_FLD_LTH = SMCODICM.LEN;
        if (SMCODICM.DEC_PNT == ' ') WS_TEMP_VARIABLE.WS_FLD.WS_FLD_DCM = 0;
        else WS_TEMP_VARIABLE.WS_FLD.WS_FLD_DCM = Short.parseShort(""+SMCODICM.DEC_PNT);
        if (SMCODICM.SIGN == 'Y') {
            WS_TEMP_VARIABLE.WS_FLD.WS_FLD_SIGN = 'S';
        } else {
            if (SMCODICM.SIGN == 'N') {
                WS_TEMP_VARIABLE.WS_FLD.WS_FLD_SIGN = ' ';
            } else {
                WS_TEMP_VARIABLE.WS_FLD.WS_FLD_SIGN = SMCODICM.SIGN;
            }
        }
        WS_TEMP_VARIABLE.WS_FLD.WS_FLD_LTH -= WS_TEMP_VARIABLE.WS_FLD.WS_FLD_DCM;
        BPCXP43.FLD_TYPE = WS_TEMP_VARIABLE.WS_FLD.WS_FLD_TYPE;
        BPCXP43.FLD_LTH = WS_TEMP_VARIABLE.WS_FLD.WS_FLD_LTH;
        BPCXP43.FLD_DCM = WS_TEMP_VARIABLE.WS_FLD.WS_FLD_DCM;
        BPCXP43.FLD_SIGN = WS_TEMP_VARIABLE.WS_FLD.WS_FLD_SIGN;
        SCCFMT.FMTID = "BPP43";
        SCCFMT.DATA_PTR = BPCXP43;
        SCCFMT.DATA_LEN = 15;
        IBS.FMT(SCCGWA, SCCFMT);
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
