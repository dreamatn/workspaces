package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZUUOD {
    String JIBS_tmp_str[] = new String[10];
    String MMO = "BP";
    String OUTPUT_FMT = "BPX01";
    String PGM_NAME = "BPZUUOD";
    String CALL_BPZFTLRQ = "BP-F-TLR-INF-QUERY ";
    String CALL_BPZRUOD = "BP-U-R-UOD         ";
    String CALL_BPZFTLAM = "BP-F-TLR-ACCU-MGM   ";
    String CALL_BPZPQBNK = "BP-P-QUERY-BANK     ";
    BPZUUOD_WS_VARIABLES WS_VARIABLES = new BPZUUOD_WS_VARIABLES();
    BPZUUOD_WS_OUTPUT_DATA WS_OUTPUT_DATA = new BPZUUOD_WS_OUTPUT_DATA();
    BPZUUOD_WS_COND_FLG WS_COND_FLG = new BPZUUOD_WS_COND_FLG();
    BPCMSG_ERROR_MSG ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    BPCOUOD BPCOUOD = new BPCOUOD();
    BPCOSUOD BPCOSUOD = new BPCOSUOD();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCFTLAM BPCFTLAM = new BPCFTLAM();
    BPCPQBNK BPCPQBNK = new BPCPQBNK();
    SCCGWA SCCGWA;
    SCCGBPA_BP_AREA BP_AREA;
    BPCOUUOD BPCOUUOD;
    public void MP(SCCGWA SCCGWA, BPCOUUOD BPCOUUOD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCOUUOD = BPCOUUOD;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        B00_MAINTAIN_PROCESS();
        CEP.TRC(SCCGWA, "BPZUUOD return!");
        Z_RET();
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_VARIABLES);
        IBS.init(SCCGWA, WS_COND_FLG);
        IBS.init(SCCGWA, BPCOUOD);
        IBS.init(SCCGWA, BPCOSUOD);
        IBS.init(SCCGWA, BPCFTLRQ);
        IBS.init(SCCGWA, BPCFTLAM);
        IBS.init(SCCGWA, BPCPQBNK);
    }
    public void B00_MAINTAIN_PROCESS() throws IOException,SQLException,Exception {
        if (BPCOUUOD.VAL.ATH_TLR.trim().length() == 0) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_ATH_TLR_SPACE;
            S000_ERR_MSG_PROC();
        }
        B010_GET_TILLER_INFORMATION();
        B020_GET_BR_UOD_LMT();
        B030_GET_LOC_CCY();
        B040_GET_TILLER_UESD_UOD_AMT();
        CEP.TRC(SCCGWA, BPCFTLAM.TOT_AMT);
        CEP.TRC(SCCGWA, BPCOUUOD.RC);
    }
    public void B010_GET_TILLER_INFORMATION() throws IOException,SQLException,Exception {
        BPCFTLRQ.INFO.TLR = BPCOUUOD.VAL.ATH_TLR;
        S000_CALL_BPZFTLRQ();
    }
    public void B020_GET_BR_UOD_LMT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCFTLRQ.INFO.TLR_BR);
        BPCOSUOD.KEY.UOD_BR = BPCFTLRQ.INFO.TLR_BR;
        BPCOUOD.FUNC = 'I';
        BPCOUOD.POINTER = BPCOSUOD;
        S000_CALL_BPZRUOD();
    }
    public void B030_GET_LOC_CCY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQBNK);
        BPCPQBNK.DATA_INFO.BNK = BPCOSUOD.KEY.UOD_BK;
        CEP.TRC(SCCGWA, BPCPQBNK.DATA_INFO.BNK);
        S000_CALL_BPZPQBNK();
    }
    public void B040_GET_TILLER_UESD_UOD_AMT() throws IOException,SQLException,Exception {
        R000_TRANS_DATA_PARAMETER();
        S000_CALL_BPZFTLAM();
        R000_TRANS_DATA_OUTPUT();
    }
    public void R000_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        BPCFTLAM.OP_CODE = 'A';
        BPCFTLAM.TLR = BPCOUUOD.VAL.ATH_TLR;
        BPCFTLAM.CCY = BPCPQBNK.DATA_INFO.LOC_CCY1;
        BPCFTLAM.ACCU_TYP = "20";
        BPCFTLAM.TX_COUNT = 1;
        BPCFTLAM.AMT = 0;
        CEP.TRC(SCCGWA, BPCFTLAM.AMT);
    }
    public void R000_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        if (BPCFTLRQ.INFO.ATH_LVL == ' ') WS_VARIABLES.CNT = 0;
        else WS_VARIABLES.CNT = Short.parseShort(""+BPCFTLRQ.INFO.ATH_LVL);
        WS_VARIABLES.CNT = (short) (WS_VARIABLES.CNT + 1);
        WS_OUTPUT_DATA.ATH_TLR = BPCOUUOD.VAL.ATH_TLR;
        WS_OUTPUT_DATA.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        WS_OUTPUT_DATA.TR_TIME = SCCGWA.COMM_AREA.TR_TIME;
        WS_OUTPUT_DATA.UOD_LMT = BPCOSUOD.VAL.UOD_AMT[WS_VARIABLES.CNT-1].UOD_LMT;
        WS_OUTPUT_DATA.USED_UOD_AMT = BPCFTLAM.TOT_AMT;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = OUTPUT_FMT;
        SCCFMT.DATA_PTR = WS_OUTPUT_DATA;
        SCCFMT.DATA_LEN = 54;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_BPZRUOD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CALL_BPZRUOD, BPCOUOD);
        if (BPCOUOD.RC.RC_CODE == 0) {
            BPCOUUOD.RC.RC_MMO = MMO;
            BPCOUUOD.RC.RC_CODE = 0;
        } else {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCOUOD.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCOUUOD.RC);
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCOUOD.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPQBNK() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCPQBNK.DATA_INFO.BNK);
        CEP.TRC(SCCGWA, BPCPQBNK.RC);
        IBS.CALLCPN(SCCGWA, CALL_BPZPQBNK, BPCPQBNK);
        CEP.TRC(SCCGWA, BPCPQBNK.RC);
        if (BPCPQBNK.RC.RC_CODE == 0) {
            BPCOUUOD.RC.RC_MMO = MMO;
            BPCOUUOD.RC.RC_CODE = 0;
        } else {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQBNK.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCOUUOD.RC);
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQBNK.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZFTLRQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CALL_BPZFTLRQ, BPCFTLRQ);
        CEP.TRC(SCCGWA, BPCFTLRQ.RC);
        if (BPCFTLRQ.RC.RC_CODE == 0) {
            BPCOUUOD.RC.RC_MMO = MMO;
            BPCOUUOD.RC.RC_CODE = 0;
        } else {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCFTLRQ.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCOUUOD.RC);
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFTLRQ.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZFTLAM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CALL_BPZFTLAM, BPCFTLAM);
        CEP.TRC(SCCGWA, BPCFTLAM.RC);
        if (BPCFTLAM.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCFTLAM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCOUUOD.RC);
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFTLAM.RC);
            S000_ERR_MSG_PROC();
        } else {
            BPCOUUOD.RC.RC_MMO = MMO;
            BPCOUUOD.RC.RC_CODE = 0;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_VARIABLES.ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCOUUOD.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCOUUOD = ");
            CEP.TRC(SCCGWA, BPCOUUOD);
        }
    } //FROM #ENDIF
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
