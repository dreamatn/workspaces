package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT4522 {
    String JIBS_tmp_str[] = new String[10];
    String K_OUTPUT_FMT = "BP406";
    String CPN_S_MAINT_ORG = "BP-S-MAINT-ORG      ";
    String CPN_P_INQ_ORG_LOW = "BP-P-INQ-ORG-LOW    ";
    String CPN_R_BRW_ORG_REL = "BP-R-BRW-ORG-REL    ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_CNT = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRORGR BPRORGR = new BPRORGR();
    BPCRBORR BPCRBORR = new BPCRBORR();
    BPCSMORG BPCSMORG = new BPCSMORG();
    BPCPORLO BPCPORLO = new BPCPORLO();
    SCCGWA SCCGWA;
    BPB4520_AWA_4520 BPB4520_AWA_4520;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT4522 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB4520_AWA_4520>");
        BPB4520_AWA_4520 = (BPB4520_AWA_4520) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB4520_AWA_4520.I_FUNC);
        CEP.TRC(SCCGWA, BPB4520_AWA_4520.PARM_TP);
        CEP.TRC(SCCGWA, BPB4520_AWA_4520.BNK);
        CEP.TRC(SCCGWA, BPB4520_AWA_4520.BR);
        CEP.TRC(SCCGWA, BPB4520_AWA_4520.EFF_DT);
        CEP.TRC(SCCGWA, BPB4520_AWA_4520.EXP_DT);
        CEP.TRC(SCCGWA, BPB4520_AWA_4520.ORG_DESC);
        CEP.TRC(SCCGWA, BPB4520_AWA_4520.ORG_CDES);
        CEP.TRC(SCCGWA, BPB4520_AWA_4520.SUPR_BR);
        CEP.TRC(SCCGWA, BPB4520_AWA_4520.ATTR);
        CEP.TRC(SCCGWA, BPB4520_AWA_4520.LVL);
        CEP.TRC(SCCGWA, BPB4520_AWA_4520.TYP);
        B010_CHECK_INPUT();
        B020_DEL_ORG_INFO();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPB4520_AWA_4520.BNK.equalsIgnoreCase("0") 
            || BPB4520_AWA_4520.BNK.charAt(0) == 0X00) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            WS_FLD_NO = BPB4520_AWA_4520.BNK_NO;
            S000_ERR_MSG_PROC();
        }
        if (BPB4520_AWA_4520.BR == 0 
            || BPB4520_AWA_4520.BR == 0X00) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            WS_FLD_NO = BPB4520_AWA_4520.BR_NO;
            S000_ERR_MSG_PROC();
        }
        if (BPB4520_AWA_4520.EFF_DT > SCCGWA.COMM_AREA.AC_DATE) {
        } else {
            IBS.init(SCCGWA, BPCPORLO);
            BPCPORLO.BNK = BPB4520_AWA_4520.BNK;
            BPCPORLO.BR = BPB4520_AWA_4520.BR;
            S000_CALL_BPZPORLO();
            if (BPCPORLO.FLAG == 'Y') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_UNDERLING_EXIST;
                WS_FLD_NO = BPB4520_AWA_4520.BR_NO;
                S000_ERR_MSG_PROC();
            }
        }
        IBS.init(SCCGWA, BPCRBORR);
        IBS.init(SCCGWA, BPRORGR);
        CEP.TRC(SCCGWA, BPB4520_AWA_4520.BNK);
        CEP.TRC(SCCGWA, BPB4520_AWA_4520.BR);
        BPRORGR.KEY.BNK = BPB4520_AWA_4520.BNK;
        BPRORGR.KEY.BR = BPB4520_AWA_4520.BR;
        B010_1_CHECK_REL();
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRBORR.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_ORGREL_NOTFND)) {
            BPRORGR.KEY.BNK = BPB4520_AWA_4520.BNK;
            BPRORGR.KEY.BR = 0;
            BPRORGR.REL_BR = BPB4520_AWA_4520.BR;
            B010_1_CHECK_REL();
        }
        BPCRBORR.FUNC = 'E';
        S000_CALL_BPZRBORR();
    }
    public void B010_1_CHECK_REL() throws IOException,SQLException,Exception {
        BPCRBORR.INFO.POINTER = BPRORGR;
        IBS.init(SCCGWA, BPCRBORR.RC);
        BPCRBORR.FUNC = 'S';
        S000_CALL_BPZRBORR();
        BPCRBORR.FUNC = 'R';
        S000_CALL_BPZRBORR();
        CEP.TRC(SCCGWA, BPCRBORR.RC.RC_CODE);
        if (BPCRBORR.RC.RC_CODE == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_REL_BR_EXIST;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_DEL_ORG_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSMORG);
        BPCSMORG.FUNC = 'D';
        BPCSMORG.FUNC_CODE = BPB4520_AWA_4520.I_FUNC;
        BPCSMORG.BNK = BPB4520_AWA_4520.BNK;
        BPCSMORG.BR = BPB4520_AWA_4520.BR;
        BPCSMORG.EFF_DT = BPB4520_AWA_4520.EFF_DT;
        BPCSMORG.OUTPUT_FMT = K_OUTPUT_FMT;
        S000_CALL_BPZSMORG();
    }
    public void S000_CALL_BPZSMORG() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = CPN_S_MAINT_ORG;
        SCCCALL.COMMAREA_PTR = BPCSMORG;
        SCCCALL.ERR_FLDNO = BPB4520_AWA_4520.BR_NO;
        IBS.CALL(SCCGWA, SCCCALL);
        if (BPCSMORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCSMORG.RC);
            WS_FLD_NO = BPB4520_AWA_4520.BR_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPORLO() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_ORG_LOW, BPCPORLO);
        if (BPCPORLO.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPORLO.RC);
            WS_FLD_NO = (short) BPB4520_AWA_4520.BR;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZRBORR() throws IOException,SQLException,Exception {
        BPCRBORR.INFO.LEN = 169;
        IBS.CALLCPN(SCCGWA, CPN_R_BRW_ORG_REL, BPCRBORR);
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, BPCRBORR.RC);
        if (BPCRBORR.RC.RC_CODE != 0 
            && !JIBS_tmp_str[1].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_ORGREL_NOTFND)) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRBORR.RC);
            S000_ERR_MSG_PROC();
        }
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
