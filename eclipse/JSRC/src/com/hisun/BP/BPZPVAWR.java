package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZPVAWR {
    BPRVWA_VCH_AREA VCH_AREA;
    int JIBS_tmp_int;
    BPRANS_ITM ITM;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String WS_ERR_MSG = " ";
    int WS_I = 0;
    String WS_TSQ = " ";
    int WS_IDX = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPRVWA BPRVWA;
    BPRANS BPRANS;
    BPCOVAWR BPCOVAWR;
    public void MP(SCCGWA SCCGWA, BPCOVAWR BPCOVAWR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCOVAWR = BPCOVAWR;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZPVAWR return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        if (BPCOVAWR.CONTROL.VCH_IND == 'Y') {
            BPRVWA = (BPRVWA) BPCOVAWR.CONTROL.VCH_PTR;
        } else {
            BPRVWA = (BPRVWA) GWA_BP_AREA.VCH_AREA.VCH_AREA_PTR;
            BPRANS = (BPRANS) GWA_BP_AREA.ANS_AREA_PTR;
        }
        IBS.init(SCCGWA, BPCOVAWR.RC);
        CEP.TRC(SCCGWA, BPCOVAWR.FST_FLG);
        CEP.TRC(SCCGWA, BPCOVAWR.PARTB.BOOK_FLG);
        CEP.TRC(SCCGWA, BPCOVAWR.PARTB.CCY);
        CEP.TRC(SCCGWA, BPCOVAWR.PARTB.ITM);
        CEP.TRC(SCCGWA, BPCOVAWR.PARTB.AC_NO);
        CEP.TRC(SCCGWA, BPCOVAWR.PARTB.SIGN);
        CEP.TRC(SCCGWA, BPCOVAWR.PARTB.AMT);
        CEP.TRC(SCCGWA, BPCOVAWR.PARTB.VAL_DATE);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHK_IPT();
        if (pgmRtn) return;
        B020_WRT_VWA();
        if (pgmRtn) return;
    }
    public void B010_CHK_IPT() throws IOException,SQLException,Exception {
        if (!(BPCOVAWR.FST_FLG == 'Y' 
            || BPCOVAWR.FST_FLG == 'N')) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_IPT_FST_FLG;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (SCCGWA.COMM_AREA.JRN_NO <= 0 
            && BPCOVAWR.CONTROL.VCH_IND != 'Y') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TXN_NOT_IND_LOG;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, GWA_BP_AREA.VCH_AREA.VCH_MAX_CNT);
        if (GWA_BP_AREA.VCH_AREA.VCH_MAX_CNT <= 0 
            && BPCOVAWR.CONTROL.VCH_IND != 'Y') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_ALLO_MAX_CNT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (GWA_BP_AREA.VCH_AREA.VCH_CNT >= GWA_BP_AREA.VCH_AREA.VCH_MAX_CNT 
            && BPCOVAWR.CONTROL.VCH_IND != 'Y') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_EXCEED_VCH_MAX_CNT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCOVAWR.PARTB.BR <= 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BR_INPUT_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (!(BPCOVAWR.PARTB.SIGN == 'D' 
            || BPCOVAWR.PARTB.SIGN == 'C')) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_VCH_DCFLG_INVALID;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCOVAWR.PARTB.AMT == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_VCH_AMT_NOT_ZERO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_WRT_VWA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRVWA.BASIC_AREA.CNT);
        CEP.TRC(SCCGWA, GWA_BP_AREA.VCH_AREA.VCH_CNT);
        BPRVWA.BASIC_AREA.CNT += 1;
        VCH_AREA = new BPRVWA_VCH_AREA();
        BPRVWA.VCH_AREA.add(VCH_AREA);
        if (BPCOVAWR.FST_FLG == 'Y') {
            VCH_AREA.CPN_CALL_SEQ = (short) SCCGWA.COMM_AREA.CALL_SEQ;
        } else {
            CEP.TRC(SCCGWA, BPRVWA.BASIC_AREA.CNT);
            if (BPRVWA.BASIC_AREA.CNT <= 1) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CNT_VWA_LT_ONE;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            } else {
                VCH_AREA.CPN_CALL_SEQ = BPRVWA.VCH_AREA.get(BPRVWA.BASIC_AREA.CNT - 1-1).CPN_CALL_SEQ;
            }
        }
        if (BPCOVAWR.CPN_CALL_SEQ > 0) {
            VCH_AREA.CPN_CALL_SEQ = BPCOVAWR.CPN_CALL_SEQ;
        }
        IBS.CLONE(SCCGWA, BPCOVAWR.PARTB, VCH_AREA.PARTB);
        IBS.CLONE(SCCGWA, BPCOVAWR.CONTROL, VCH_AREA.CONTROL);
        if (BPCOVAWR.CONTROL.VCH_IND != 'Y') {
            if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
                VCH_AREA.PARTB.POST_DATE = BPCOVAWR.PARTB.POST_DATE;
            } else {
                VCH_AREA.PARTB.POST_DATE = BPCOVAWR.PARTB.VAL_DATE;
            }
        }
        if (BPRVWA.BASIC_AREA.REDEFINES17.MMDP_FWD_FLG == 'Y') {
            VCH_AREA.PARTB.FLR = "Y";
            if (VCH_AREA.PARTB.FLR == null) VCH_AREA.PARTB.FLR = "";
            JIBS_tmp_int = VCH_AREA.PARTB.FLR.length();
            for (int i=0;i<60-JIBS_tmp_int;i++) VCH_AREA.PARTB.FLR += " ";
            VCH_AREA.PARTB.FLR = VCH_AREA.PARTB.FLR.substring(0, 3 - 1) + "Y" + VCH_AREA.PARTB.FLR.substring(3 + 1 - 1);
        }
        if (BPRVWA.BASIC_AREA.ODE_FLG == 'Y' 
            && BPCOVAWR.CONTROL.VCH_IND != 'Y') {
            BPRANS.CNT = (short) BPRVWA.BASIC_AREA.CNT;
            ITM = new BPRANS_ITM();
            BPRANS.VCH_AREA.ITM.add(ITM);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCOVAWR.ANS_AREA);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], ITM);
        }
        CEP.TRC(SCCGWA, "WRITE VWA DONE");
        CEP.TRC(SCCGWA, BPRVWA.BASIC_AREA.CNT);
        CEP.TRC(SCCGWA, "EACH VWA INFO START");
        CEP.TRC(SCCGWA, BPRVWA.VCH_AREA.get(BPRVWA.BASIC_AREA.CNT-1).CONTROL.AC_FLG);
        CEP.TRC(SCCGWA, BPRVWA.VCH_AREA.get(BPRVWA.BASIC_AREA.CNT-1).PARTB.BOOK_FLG);
        CEP.TRC(SCCGWA, BPRVWA.VCH_AREA.get(BPRVWA.BASIC_AREA.CNT-1).PARTB.CCY);
        CEP.TRC(SCCGWA, BPRVWA.VCH_AREA.get(BPRVWA.BASIC_AREA.CNT-1).PARTB.ITM);
        CEP.TRC(SCCGWA, BPRVWA.VCH_AREA.get(BPRVWA.BASIC_AREA.CNT-1).PARTB.AC_NO);
        CEP.TRC(SCCGWA, BPRVWA.VCH_AREA.get(BPRVWA.BASIC_AREA.CNT-1).PARTB.MIB_NO);
        CEP.TRC(SCCGWA, BPRVWA.VCH_AREA.get(BPRVWA.BASIC_AREA.CNT-1).PARTB.SIGN);
        CEP.TRC(SCCGWA, BPRVWA.VCH_AREA.get(BPRVWA.BASIC_AREA.CNT-1).PARTB.AMT);
        CEP.TRC(SCCGWA, BPRVWA.VCH_AREA.get(BPRVWA.BASIC_AREA.CNT-1).PARTB.VAL_DATE);
        CEP.TRC(SCCGWA, BPRVWA.VCH_AREA.get(BPRVWA.BASIC_AREA.CNT-1).PARTB.EVENT_CODE);
        CEP.TRC(SCCGWA, BPRVWA.VCH_AREA.get(BPRVWA.BASIC_AREA.CNT-1).PARTB.RES_CENT);
        CEP.TRC(SCCGWA, BPRVWA.VCH_AREA.get(BPRVWA.BASIC_AREA.CNT-1).PARTB.LINE);
        CEP.TRC(SCCGWA, BPRVWA.VCH_AREA.get(BPRVWA.BASIC_AREA.CNT-1).PARTB.INT_DEALINGS);
        CEP.TRC(SCCGWA, BPRVWA.VCH_AREA.get(BPRVWA.BASIC_AREA.CNT-1).PARTB.RESERVE);
        CEP.TRC(SCCGWA, "EACH VWA INFO END");
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_ERR_MSG);
        IBS.CPY2CLS(SCCGWA, WS_ERR_MSG, BPCOVAWR.RC);
        Z_RET();
        if (pgmRtn) return;
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCOVAWR.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCOVAWR = ");
            CEP.TRC(SCCGWA, BPCOVAWR);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
