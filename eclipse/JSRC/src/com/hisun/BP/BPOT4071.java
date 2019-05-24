package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT4071 {
    String K_CPN_S_MAINTAIN_TWND = "BP-S-MAINTAIN-TWND  ";
    String CPN_SCSSCKDT = "SCSSCKDT";
    String CPN_BP_I_INQ_CITY = "BP-I-INQ-CITY     ";
    String K_FMT_CD_1 = "BPX01";
    String K_FMT_CD_2 = "BP747";
    String K_PUBLIC_TYPE = "EVENT";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_CNT = 0;
    int WS_I = 0;
    int WS_J = 0;
    int WS_TMP = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSACM BPCSACM = new BPCSACM();
    BPCPQPDM BPCPQPDM = new BPCPQPDM();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCOQPCD BPCOQPCD = new BPCOQPCD();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB4070_AWA_4070 BPB4070_AWA_4070;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT4071 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB4070_AWA_4070>");
        BPB4070_AWA_4070 = (BPB4070_AWA_4070) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        IBS.init(SCCGWA, BPCSACM);
        BPCSACM.FUNC = 'A';
        BPCSACM.KEY.CNTR_TYPE = BPB4070_AWA_4070.CNTR_TYP;
        if (BPB4070_AWA_4070.PROD_TYP.equalsIgnoreCase("*")) {
            BPCSACM.KEY.PROD_TYPE = " ";
        } else {
            BPCSACM.KEY.PROD_TYPE = BPB4070_AWA_4070.PROD_TYP;
        }
        if (BPB4070_AWA_4070.BR == 999999) {
            BPCSACM.KEY.BR = 0;
        } else {
            BPCSACM.KEY.BR = BPB4070_AWA_4070.BR;
        }
        BPCSACM.DATA.MOD_NO = BPB4070_AWA_4070.MOD_NO;
        BPCSACM.DATA.MOD_NAME = BPB4070_AWA_4070.MOD_NAME;
        BPCSACM.DATA.MOD_TYP = BPB4070_AWA_4070.MOD_TYP;
        BPCSACM.EFF_DT = BPB4070_AWA_4070.EFF_DT;
        BPCSACM.EXP_DT = BPB4070_AWA_4070.EXP_DT;
        CEP.TRC(SCCGWA, BPB4070_AWA_4070.EFF_DT);
        CEP.TRC(SCCGWA, BPB4070_AWA_4070.EXP_DT);
        for (WS_CNT = 1; WS_CNT <= 60; WS_CNT += 1) {
            if (BPB4070_AWA_4070.EVENT[WS_CNT-1].EV_TYPE.trim().length() > 0) {
                IBS.init(SCCGWA, BPCOQPCD);
                BPCOQPCD.INPUT_DATA.TYPE = K_PUBLIC_TYPE;
                BPCOQPCD.INPUT_DATA.CODE = BPB4070_AWA_4070.EVENT[WS_CNT-1].EV_TYPE;
                S010_CALL_BPZPQPCD();
                if (BPCOQPCD.RC.RC_CODE != 0) {
                    WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCOQPCD.RC);
                    WS_FLD_NO = BPB4070_AWA_4070.EVENT[WS_CNT-1].EV_TYPE_NO;
                    S000_ERR_MSG_PROC();
                }
            }
            BPCSACM.DATA.EVENT[WS_CNT-1].EVENT_TYPE = BPB4070_AWA_4070.EVENT[WS_CNT-1].EV_TYPE;
        }
        S010_CALL_BPZSACM();
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPB4070_AWA_4070.CNTR_TYP.trim().length() > 0 
            && !BPB4070_AWA_4070.CNTR_TYP.equalsIgnoreCase("IB") 
            && !BPB4070_AWA_4070.CNTR_TYP.equalsIgnoreCase("GL") 
            && !BPB4070_AWA_4070.CNTR_TYP.equalsIgnoreCase("FEEV")) {
            IBS.init(SCCGWA, BPCPQPDM);
            BPCPQPDM.PRDT_MODEL = BPB4070_AWA_4070.CNTR_TYP;
            S010_CALL_BPZPQPDM();
            if (BPCPQPDM.RC.RC_CODE != 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CNTR_TYPE_NOT_FOUND;
                WS_FLD_NO = BPB4070_AWA_4070.CNTR_TYP_NO;
                S000_ERR_MSG_PROC();
            }
        }
        if (BPB4070_AWA_4070.PROD_TYP.trim().length() > 0 
            && !BPB4070_AWA_4070.PROD_TYP.equalsIgnoreCase("*")) {
            IBS.init(SCCGWA, BPCPQPRD);
            BPCPQPRD.PRDT_CODE = BPB4070_AWA_4070.PROD_TYP;
            S010_CALL_BPZPQPRD();
            if (BPCPQPRD.RC.RC_CODE != 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PROD_NOT_EXIST;
                WS_FLD_NO = BPB4070_AWA_4070.PROD_TYP_NO;
                S000_ERR_MSG_PROC();
            }
        }
        if (BPB4070_AWA_4070.BR != 0 
            && BPB4070_AWA_4070.BR != 999999) {
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BR = BPB4070_AWA_4070.BR;
            S010_CALL_BPZPQORG();
            if (BPCPQORG.RC.RC_CODE != 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BR_NOT_EXIST;
                WS_FLD_NO = BPB4070_AWA_4070.BR_NO;
                S000_ERR_MSG_PROC();
            }
        }
        if (BPB4070_AWA_4070.MOD_TYP != 'C' 
            && BPB4070_AWA_4070.MOD_TYP != 'O') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INVALID_MOD_TYP);
        }
        for (WS_I = 1; WS_I <= 59; WS_I += 1) {
            WS_TMP = WS_I + 1;
            for (WS_J = WS_TMP; WS_J <= 60; WS_J += 1) {
                CEP.TRC(SCCGWA, BPB4070_AWA_4070.EVENT[WS_I-1].EV_TYPE);
                CEP.TRC(SCCGWA, BPB4070_AWA_4070.EVENT[WS_J-1].EV_TYPE);
                if (BPB4070_AWA_4070.EVENT[WS_I-1].EV_TYPE.trim().length() > 0 
                    && BPB4070_AWA_4070.EVENT[WS_J-1].EV_TYPE.trim().length() > 0) {
                    if (BPB4070_AWA_4070.EVENT[WS_I-1].EV_TYPE.equalsIgnoreCase(BPB4070_AWA_4070.EVENT[WS_J-1].EV_TYPE)) {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_EV_CODE_EQUAL;
                        WS_FLD_NO = BPB4070_AWA_4070.EVENT[WS_J-1].EV_TYPE_NO;
                        S000_ERR_MSG_PROC();
                    }
                }
            }
        }
    }
    public void S010_CALL_BPZPQPDM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-PRD-MODEL", BPCPQPDM);
        CEP.TRC(SCCGWA, BPCPQPDM.RC);
    }
    public void S010_CALL_BPZPQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-PRDT-INFO", BPCPQPRD);
        CEP.TRC(SCCGWA, BPCPQPRD.RC);
    }
    public void S010_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        CEP.TRC(SCCGWA, BPCPQORG.RC);
    }
    public void S010_CALL_BPZPQPCD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-PC", BPCOQPCD);
        CEP.TRC(SCCGWA, BPCOQPCD.RC);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S010_CALL_BPZSACM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-MAINT-ACM", BPCSACM);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
