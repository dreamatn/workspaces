package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT9204 {
    String K_CPN_BP_S_INQ_UPDATA = "BP-S-INQ-UPDATA     ";
    String CPN_P_SET_SBUS_TRN = "SC-SET-SUBS-TRANS";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCSUBS SCCSUBS = new SCCSUBS();
    BPCSQUPD BPCSQUPD = new BPCSQUPD();
    SCCGWA SCCGWA;
    BPB9204_AWA_9204 BPB9204_AWA_9204;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT9204 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB9204_AWA_9204>");
        BPB9204_AWA_9204 = (BPB9204_AWA_9204) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_TUPF_INFO_QUERY();
        B030_SET_SUB_TRN();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B020_TUPF_INFO_QUERY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSQUPD);
        BPCSQUPD.FUNC = 'Q';
        BPCSQUPD.KEY.BATCH_NO = BPB9204_AWA_9204.BAT_NO;
        BPCSQUPD.KEY.SEQ = BPB9204_AWA_9204.SEQ_NO;
        BPCSQUPD.TYPE = BPB9204_AWA_9204.UP_TYPE;
        S010_CALL_BPZSQUPD();
    }
    public void S010_CALL_BPZSQUPD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, K_CPN_BP_S_INQ_UPDATA, BPCSQUPD);
    }
    public void B030_SET_SUB_TRN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCSUBS);
        SCCSUBS.AP_CODE = 999;
        if (BPB9204_AWA_9204.UP_TYPE.equalsIgnoreCase("01")) {
            if (BPB9204_AWA_9204.FUNC == 'I') {
                SCCSUBS.TR_CODE = 9251;
            }
            if (BPB9204_AWA_9204.FUNC == 'M') {
                SCCSUBS.TR_CODE = 9252;
            }
            if (BPB9204_AWA_9204.FUNC == 'D') {
                SCCSUBS.TR_CODE = 9254;
            }
        } else if (BPB9204_AWA_9204.UP_TYPE.equalsIgnoreCase("02")) {
            if (BPB9204_AWA_9204.FUNC == 'I') {
                SCCSUBS.TR_CODE = 9207;
            }
            if (BPB9204_AWA_9204.FUNC == 'M') {
                SCCSUBS.TR_CODE = 9208;
            }
            if (BPB9204_AWA_9204.FUNC == 'D') {
                SCCSUBS.TR_CODE = 9215;
            }
        } else if (BPB9204_AWA_9204.UP_TYPE.equalsIgnoreCase("03")) {
            if (BPB9204_AWA_9204.FUNC == 'I') {
                SCCSUBS.TR_CODE = 9212;
            }
            if (BPB9204_AWA_9204.FUNC == 'M') {
                SCCSUBS.TR_CODE = 9213;
            }
            if (BPB9204_AWA_9204.FUNC == 'D') {
                SCCSUBS.TR_CODE = 9221;
            }
        } else if (BPB9204_AWA_9204.UP_TYPE.equalsIgnoreCase("04")) {
            if (BPB9204_AWA_9204.FUNC == 'I') {
                SCCSUBS.TR_CODE = 9210;
            }
            if (BPB9204_AWA_9204.FUNC == 'M') {
                SCCSUBS.TR_CODE = 9211;
            }
            if (BPB9204_AWA_9204.FUNC == 'D') {
                SCCSUBS.TR_CODE = 9219;
            }
        } else if (BPB9204_AWA_9204.UP_TYPE.equalsIgnoreCase("05")) {
            if (BPB9204_AWA_9204.FUNC == 'I') {
                SCCSUBS.TR_CODE = 9226;
            }
            if (BPB9204_AWA_9204.FUNC == 'M') {
                SCCSUBS.TR_CODE = 9227;
            }
            if (BPB9204_AWA_9204.FUNC == 'D') {
                SCCSUBS.TR_CODE = 9223;
            }
        } else {
        }
        S000_SET_SUBS_TRN();
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_SET_SUBS_TRN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_SET_SBUS_TRN, SCCSUBS);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
