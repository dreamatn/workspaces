package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT5082 {
    String CPN_S_EXPT = "BP-DEF-EXPT      ";
    String CPN_INQUIRE_CCY = "BP-INQUIRE-CCY    ";
    String CPN_INQ_PUB_PARM = "BP-PARM-READ      ";
    String CPN_INQ_PUB_CODE = "BP-P-INQ-PC       ";
    String CPN_R_TQPH = "BP-R-TQPH-M      ";
    String CPN_R_TQP = "BP-R-TQP-M       ";
    String WS_CCY = " ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPREXRT BPREXRT = new BPREXRT();
    BPCQCCY BPCQCCY = new BPCQCCY();
    BPCEXPT BPCEXPT = new BPCEXPT();
    BPCIFQ BPCIFQ = new BPCIFQ();
    BPCTQPHM BPCTQPHM = new BPCTQPHM();
    BPCTTQPM BPCTTQPM = new BPCTTQPM();
    BPRTQP BPRTQP = new BPRTQP();
    BPRTQPH BPRTQPH = new BPRTQPH();
    BPRTQPH BPRTQPHO = new BPRTQPH();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB5082_AWA_5082 BPB5082_AWA_5082;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT5082 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB5082_AWA_5082>");
        BPB5082_AWA_5082 = (BPB5082_AWA_5082) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_DEL_REC_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPREXRT);
        IBS.init(SCCGWA, BPCPRMR);
        BPREXRT.KEY.TYP = "EXRT";
        BPREXRT.KEY.CD = BPB5082_AWA_5082.EXR_TYP;
        S000_CALL_BPZPRMR();
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_EXR_TYPE;
            WS_FLD_NO = BPB5082_AWA_5082.EXR_TYP_NO;
            S000_ERR_MSG_PROC();
        }
        WS_CCY = BPB5082_AWA_5082.CCY;
        WS_FLD_NO = BPB5082_AWA_5082.CCY_NO;
        R000_CHECK_CCY();
        CEP.TRC(SCCGWA, BPREXRT.DAT_TXT.BASE_CCY);
        if (BPREXRT.DAT_TXT.BASE_CCY.equalsIgnoreCase(BPB5082_AWA_5082.CCY)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CCY_NOT_DEF_EX_CCY;
            WS_FLD_NO = BPB5082_AWA_5082.CCY_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_DEL_REC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCEXPT);
        BPCEXPT.FUNC = 'D';
        BPCEXPT.BR = BPB5082_AWA_5082.BR;
        BPCEXPT.EXR_TYP = BPB5082_AWA_5082.EXR_TYP;
        BPCEXPT.CCY = BPB5082_AWA_5082.CCY;
        BPCEXPT.CMP_FLG = BPB5082_AWA_5082.CMP_FLG;
        BPCEXPT.CCS_BP = BPB5082_AWA_5082.CCS_BP;
        BPCEXPT.CFX_BP = BPB5082_AWA_5082.CFX_BP;
        BPCEXPT.CCS_SP = BPB5082_AWA_5082.CCS_SP;
        BPCEXPT.CFX_SP = BPB5082_AWA_5082.CFX_SP;
        BPCEXPT.CS_BP = BPB5082_AWA_5082.CS_BP;
        BPCEXPT.FX_BP = BPB5082_AWA_5082.FX_BP;
        BPCEXPT.CS_SP = BPB5082_AWA_5082.CS_SP;
        BPCEXPT.FX_SP = BPB5082_AWA_5082.FX_SP;
        S000_CALL_BPZEXPT();
        B030_UPD_FX_RATE();
    }
    public void B030_UPD_FX_RATE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCIFQ);
        BPCIFQ.DATA.EXR_TYPE = BPB5082_AWA_5082.EXR_TYP;
        BPCIFQ.DATA.BR = BPB5082_AWA_5082.BR;
        BPCIFQ.DATA.CCY = BPB5082_AWA_5082.CCY;
        if (BPB5082_AWA_5082.EXR_TYP.equalsIgnoreCase("TRE") 
            || BPB5082_AWA_5082.EXR_TYP.equalsIgnoreCase("MDR")) {
            BPCIFQ.DATA.CORR_CCY = "156";
        }
        S000_CALL_BPZSIFQ();
        CEP.TRC(SCCGWA, BPCIFQ.RC.RTNCODE);
        if (BPCIFQ.RC.RTNCODE == 0) {
            B040_UPD_BPTTQPH();
            CEP.TRC(SCCGWA, BPCTQPHM.RETURN_INFO);
            if (BPCTQPHM.RETURN_INFO == 'F') {
                B050_DEL_BPTTQP();
            }
        }
    }
    public void B040_UPD_BPTTQPH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTQPH);
        IBS.init(SCCGWA, BPCTQPHM);
        BPRTQPH.KEY.EXP_DT = 99991231;
        BPRTQPH.KEY.EXP_TM = 235959;
        BPRTQPH.KEY.EXR_TYP = BPB5082_AWA_5082.EXR_TYP;
        BPRTQPH.KEY.BR = BPB5082_AWA_5082.BR;
        BPRTQPH.KEY.CCY = BPB5082_AWA_5082.CCY;
        BPRTQPH.KEY.CORR_CCY = "156";
        BPRTQPH.EFF_DT = BPCIFQ.DATA.EFF_DT;
        BPRTQPH.EFF_TM = BPCIFQ.DATA.EFF_TM;
        BPCTQPHM.INFO.FUNC = 'R';
        S000_CALL_BPZTQPHM();
        CEP.TRC(SCCGWA, BPCTQPHM.RETURN_INFO);
        if (BPCTQPHM.RETURN_INFO == 'F') {
            IBS.CLONE(SCCGWA, BPRTQPH, BPRTQPHO);
            BPCTQPHM.INFO.FUNC = 'D';
            S000_CALL_BPZTQPHM();
            IBS.CLONE(SCCGWA, BPRTQPHO, BPRTQPH);
            BPRTQPH.KEY.EXP_DT = SCCGWA.COMM_AREA.AC_DATE;
            BPRTQPH.KEY.EXP_TM = SCCGWA.COMM_AREA.TR_TIME;
            BPCTQPHM.INFO.FUNC = 'C';
            S000_CALL_BPZTQPHM();
        }
    }
    public void B050_DEL_BPTTQP() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTQP);
        IBS.init(SCCGWA, BPCTTQPM);
        BPRTQP.KEY.BR = BPB5082_AWA_5082.BR;
        BPRTQP.KEY.EXR_TYP = BPB5082_AWA_5082.EXR_TYP;
        BPRTQP.KEY.CCY = BPB5082_AWA_5082.CCY;
        BPRTQP.KEY.CORR_CCY = "156";
        BPRTQP.KEY.EFF_DT = BPCIFQ.DATA.EFF_DT;
        BPRTQP.KEY.EFF_TM = BPCIFQ.DATA.EFF_TM;
        BPCTTQPM.INFO.FUNC = 'R';
        S000_CALL_BPZTTQPM();
        if (BPCTTQPM.RETURN_INFO == 'F') {
            BPCTTQPM.INFO.FUNC = 'E';
            S000_CALL_BPZTTQPM();
        }
    }
    public void S000_CALL_BPZEXPT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_EXPT, BPCEXPT);
        if (BPCEXPT.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCEXPT.RC);
        }
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        BPCPRMR.DAT_PTR = BPREXRT;
        IBS.CALLCPN(SCCGWA, CPN_INQ_PUB_PARM, BPCPRMR);
    }
    public void R000_CHECK_CCY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCQCCY);
        BPCQCCY.DATA.CCY = WS_CCY;
        IBS.CALLCPN(SCCGWA, CPN_INQUIRE_CCY, BPCQCCY);
        if (BPCQCCY.RC.RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCQCCY.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZSIFQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-INQ-FX-QTP", BPCIFQ);
    }
    public void S000_CALL_BPZTQPHM() throws IOException,SQLException,Exception {
        BPCTQPHM.INFO.POINTER = BPRTQPH;
        BPCTQPHM.INFO.LEN = 412;
        IBS.CALLCPN(SCCGWA, CPN_R_TQPH, BPCTQPHM);
        CEP.TRC(SCCGWA, BPCTQPHM.RC);
        CEP.TRC(SCCGWA, BPCTQPHM.RETURN_INFO);
        CEP.TRC(SCCGWA, BPCTQPHM.INFO.FUNC);
    }
    public void S000_CALL_BPZTTQPM() throws IOException,SQLException,Exception {
        BPCTTQPM.POINTER = BPRTQP;
        BPCTTQPM.LEN = 401;
        IBS.CALLCPN(SCCGWA, CPN_R_TQP, BPCTTQPM);
        CEP.TRC(SCCGWA, BPCTTQPM.RETURN_INFO);
        CEP.TRC(SCCGWA, BPCTTQPM.RC);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
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
