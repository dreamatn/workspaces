package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;

public class BPZFSCOM {
    boolean pgmRtn = false;
    String CPN_U_MAINTAIN_FCOM = "BP-F-U-MAINTAIN-FCOM";
    String CPN_U_MAINTAIN_FBAS = "BP-F-U-MAINTAIN-FBAS";
    String CPN_U_MAINTAIN_FFAV = "BP-F-U-MAINTAIN-FFAV";
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG";
    String CPN_P_INQ_PGNC = "BP-P-INQ-RGNC";
    String WS_ERR_MSG = " ";
    short WS_CNT = 0;
    BPZFSCOM_WS_OUTPUT_LIST WS_OUTPUT_LIST = new BPZFSCOM_WS_OUTPUT_LIST();
    char WS_TBL_BANK_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    BPCOFBAS BPCOUBAS = new BPCOFBAS();
    BPCOCOMO BPCOCOMO = new BPCOCOMO();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCPQRGC BPCPQRGC = new BPCPQRGC();
    BPCOFFAV BPCOUFAV = new BPCOFFAV();
    BPCOFCOM BPCOTCOM = new BPCOFCOM();
    SCCGWA SCCGWA;
    BPCOFCOM BPCOSCOM;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCOFCOM BPCOSCOM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCOSCOM = BPCOSCOM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZFSCOM return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.SC_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCOSCOM.FUNC == 'I') {
            B000_01_QUERY_PROCESS();
            if (pgmRtn) return;
            B010_QUERY_PROCESS();
            if (pgmRtn) return;
        } else if (BPCOSCOM.FUNC == 'A') {
            B000_01_QUERY_PROCESS();
            if (pgmRtn) return;
            B000_02_CHECK_PROCESS();
            if (pgmRtn) return;
            B020_CREATE_PROCESS();
            if (pgmRtn) return;
        } else if (BPCOSCOM.FUNC == 'U') {
            B000_01_QUERY_PROCESS();
            if (pgmRtn) return;
            B000_02_CHECK_PROCESS();
            if (pgmRtn) return;
            B030_MODIFY_PROCESS();
            if (pgmRtn) return;
        } else if (BPCOSCOM.FUNC == 'D') {
            B000_01_QUERY_PROCESS();
            if (pgmRtn) return;
            B040_DELETE_PROCESS();
            if (pgmRtn) return;
        } else if (BPCOSCOM.FUNC == 'B') {
            B050_BROWSE_PROCESS();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        B060_TRANS_DATA_OUTPUT();
        if (pgmRtn) return;
    }
    public void B000_01_QUERY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOUBAS);
        BPCOUBAS.KEY.FEE_CODE = BPCOSCOM.KEY.FEE_CODE;
        CEP.TRC(SCCGWA, BPCOUBAS.KEY.FEE_CODE);
        BPCOUBAS.FUNC = 'I';
        S000_CALL_BPZFUBAS();
        if (pgmRtn) return;
        BPCOSCOM.KEY.FEE_CODE = BPCOUBAS.KEY.FEE_CODE;
        WS_OUTPUT_LIST.WS_FEE_DESC = BPCOUBAS.VAL.FEE_DESC;
        CEP.TRC(SCCGWA, BPCOSCOM.KEY.FEE_CODE);
    }
    public void B000_02_CHECK_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCOUBAS.VAL.AREA_TYPE);
        CEP.TRC(SCCGWA, BPCOSCOM.KEY.REG_CODE);
        if (BPCOUBAS.VAL.AREA_TYPE.equalsIgnoreCase("00")) {
            if (BPCOSCOM.KEY.REG_CODE.trim().length() > 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_REGCD_NOT_ONLY;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (BPCOUBAS.VAL.FEE_UPD_FLG == '1') {
            if (SCCGWA.COMM_AREA.BR_DP.TR_BRANCH != SCCGWA.COMM_AREA.HQT_BANK) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_HQTBANK;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (BPCOUBAS.VAL.AREA_TYPE.equalsIgnoreCase("99") 
            && BPCOSCOM.KEY.REG_CODE.trim().length() > 0) {
            IBS.init(SCCGWA, BPCPQORG);
            CEP.TRC(SCCGWA, BPCOSCOM.KEY.REG_CODE);
            if (BPCOSCOM.KEY.REG_CODE.trim().length() == 0) BPCPQORG.BR = 0;
            else BPCPQORG.BR = Integer.parseInt(BPCOSCOM.KEY.REG_CODE);
            S000_CALL_BPZPQORG();
            if (pgmRtn) return;
        }
        if (!BPCOUBAS.VAL.AREA_TYPE.equalsIgnoreCase("99") 
            && !BPCOUBAS.VAL.AREA_TYPE.equalsIgnoreCase("00") 
            && BPCOSCOM.KEY.REG_CODE.trim().length() > 0) {
            IBS.init(SCCGWA, BPCPQRGC);
            CEP.TRC(SCCGWA, BPCOSCOM.KEY.REG_CODE);
            IBS.CPY2CLS(SCCGWA, BPCOSCOM.KEY.REG_CODE, BPCPQRGC.RGN_NO);
            CEP.TRC(SCCGWA, BPCPQRGC.RGN_NO.RGN_TYPE);
            CEP.TRC(SCCGWA, BPCPQRGC.RGN_NO.RGN_SEQ);
            CEP.TRC(SCCGWA, BPCPQRGC.BNK);
            S000_CALL_BPZPQRGC();
            if (pgmRtn) return;
        }
        for (WS_CNT = 1; WS_CNT <= 9; WS_CNT += 1) {
            if (BPCOSCOM.VAL.FAV_DATA[WS_CNT-1].FAV_CODE.trim().length() > 0) {
