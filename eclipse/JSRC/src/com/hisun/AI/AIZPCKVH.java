package com.hisun.AI;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.sql.SQLException;
import com.hisun.SC.*;
import com.hisun.BP.*;
import com.hisun.IB.*;

public class AIZPCKVH {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    BPRVWA_VCH_AREA VCH_AREA;
    AICITMA_ITEM_DATA ITEM_DATA;
    AIZPCKVH_WS_ITM WS_ITM;
    DBParm AITITUS_RD;
    brParm AITITUS_BR = new brParm();
    boolean pgmRtn = false;
    String K_CODE_FMD = "FMD";
    String PGM_SCSSCKDT = "SCSSCKDT";
    int K_OCCURS_SET_MAX_ONL = 400;
    int K_OCCURS_SET_MAX_BAT = 6000;
    short WS_ITM_MAX_CNT = 4000;
    char K_SEPARATE_FLG = 'N';
    String K_NON_VIL_FLG = "043";
    String K_NON_TRA_FLG = "00";
    short WS_ITM_CNT = 0;
    List<AIZPCKVH_WS_ITM> WS_ITML = new ArrayList<AIZPCKVH_WS_ITM>();
    int WS_OCCURS_SET_MAX = 0;
    int WS_ITM_CNT_T = 0;
    AIZPCKVH_WS_COUNTER WS_COUNTER = new AIZPCKVH_WS_COUNTER();
    AIZPCKVH_WS_OLD_DATA WS_OLD_DATA = new AIZPCKVH_WS_OLD_DATA();
    String WS_COMM_ERR_MSG = " ";
    int WS_ACCUMT_CNT = 0;
    int WS_MACCUMT_CNT = 0;
    int WS_VIL_CNT = 0;
    int WS_INT_NUM = 0;
    AIZPCKVH_WS_SET_NO WS_SET_NO = new AIZPCKVH_WS_SET_NO();
    AIZPCKVH_WS_OPEN_PERIOD WS_OPEN_PERIOD = new AIZPCKVH_WS_OPEN_PERIOD();
    String WS_BOOK_FLG = " ";
    int WS_BR = 0;
    int WS_LAST_BR = 0;
    int WS_LAST_TR_BR = 0;
    String WS_LAST_VIL = " ";
    String WS_TX_VIL = " ";
    String WS_LAST_TRA = " ";
    String WS_TX_TRA = " ";
    char WS_VTR_BR_FLG = ' ';
    String WS_CCY = " ";
    AIZPCKVH_WS_TACCUM WS_TACCUM = new AIZPCKVH_WS_TACCUM();
    int WS_ACCUM_CNT = 0;
    AIZPCKVH_WS_ACCUM[] WS_ACCUM = new AIZPCKVH_WS_ACCUM[6000];
    AIZPCKVH_WS_VIL_ACUM[] WS_VIL_ACUM = new AIZPCKVH_WS_VIL_ACUM[400];
    AIZPCKVH_WS_VIL_TMP_DATA WS_VIL_TMP_DATA = new AIZPCKVH_WS_VIL_TMP_DATA();
    int WS_I0 = 0;
    int WS_I1 = 0;
    int WS_I2 = 0;
    int WS_I3 = 0;
    int WS_I4 = 0;
    AIZPCKVH_WS_COMPA_KEY WS_COMPA_KEY = new AIZPCKVH_WS_COMPA_KEY();
    AIZPCKVH_WS_COMPB_KEY WS_COMPB_KEY = new AIZPCKVH_WS_COMPB_KEY();
    char WS_USE_MTH_FLG = ' ';
    char WS_VCH_SET_END_FLG = ' ';
    char WS_VAL_CHK_FLG = ' ';
    char WS_VAL_CHK_ERR_FLG = ' ';
    char WS_BOOK_FND_FLG = ' ';
    char WS_BR_FND_FLG = ' ';
    char WS_CCY_FND_FLG = ' ';
    char WS_SIGN_DRCR_FLG = ' ';
    char WS_INTF_MODE = ' ';
    char WS_MEMO_BAL_CHK_FLG = ' ';
    char WS_ITM_RM_FLG = ' ';
    char WS_FND_AUTH_FLG = ' ';
    char WS_AUTH_RECOND_FLG = ' ';
    char WS_GOINTO_FLG = ' ';
    char WS_VIL_FLG = ' ';
    char WS_VIL_FOUND_FLG = ' ';
    char WS_COR_FOUND_FLG = ' ';
    String WS_ERR_MSG = " ";
    String WS_ERR_INFO = " ";
    String WS_INFO = " ";
    int WS_I = 0;
    int WS_CNT = 0;
    int WS_J = 0;
    int WS_FMD_BR = 0;
    AIZPCKVH_WS_INQ_DATA WS_INQ_DATA = new AIZPCKVH_WS_INQ_DATA();
    AIZPCKVH_WS_CHK_DATA WS_CHK_DATA = new AIZPCKVH_WS_CHK_DATA();
    AIZPCKVH_WS_SUS_INFO_USE WS_SUS_INFO_USE = new AIZPCKVH_WS_SUS_INFO_USE();
    String WS_INTR_ITM = " ";
    AIZPCKVH_WS_CRS_SUS_INFO WS_CRS_SUS_INFO = new AIZPCKVH_WS_CRS_SUS_INFO();
    int WS_MEMO_QBKPM_CNT = 0;
    AIZPCKVH_WS_INTERNAL_AC WS_INTERNAL_AC = new AIZPCKVH_WS_INTERNAL_AC();
    List<AIZPCKVH_WS_MEMO_BOOK_FLG> WS_MEMO_BOOK_FLG = new ArrayList<AIZPCKVH_WS_MEMO_BOOK_FLG>();
    char WS_VAWR_FST_FLG = ' ';
    char WS_SUS_FLG = ' ';
    char WS_INTR_MEMO_ITM_FLG = ' ';
    char WS_DRCR_FLG = ' ';
    char WS_ODE_FLG = ' ';
    char WS_ERR_SUS_FLG = ' ';
    char WS_BUSINESS_MSG_LVL = ' ';
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    AICITMA AICITMB = new AICITMA();
    AICPQITM AICPQITM = new AICPQITM();
    BPCQCCY BPCQCCY = new BPCQCCY();
    BPCOVAWR BPCOVAWR = new BPCOVAWR();
    AICOVLOG AICOVLOG = new AICOVLOG();
    BPCPGDIN BPCPGDIN = new BPCPGDIN();
    AIRPAI1 AIRPAI1 = new AIRPAI1();
    BPCPRMR BPCPRMR = new BPCPRMR();
    AIRAIVH AIRAIVX = new AIRAIVH();
    BPCPQORG BPCPQORG = new BPCPQORG();
    SCCCKDT SCCCKDT = new SCCCKDT();
    BPCQBKPM BPCQBKPM = new BPCQBKPM();
    BPCPQBAI BPCPQBAI = new BPCPQBAI();
    AICOCKOP AICOCKOP = new AICOCKOP();
    BPRTRT BPRTRT = new BPRTRT();
    AIRPAI7 AIRPAI7 = new AIRPAI7();
    AICUPRVS AICUPRVS = new AICUPRVS();
    AICPQMIB AICPQMIB = new AICPQMIB();
    SCCIMSG SCCIMSG = new SCCIMSG();
    IBCCASH IBCCASH = new IBCCASH();
    AIRITUS AIRITUS = new AIRITUS();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    BPRVWA BPRVWA;
    AICITMA AICITMA;
    AICOCKVH AICOCKVH;
    SCRCWAT SCRCWA;
    BPCPORUP_DATA_INFO BPCPORUP;
    public AIZPCKVH() {
        for (int i=0;i<6000;i++) WS_ACCUM[i] = new AIZPCKVH_WS_ACCUM();
        for (int i=0;i<400;i++) WS_VIL_ACUM[i] = new AIZPCKVH_WS_VIL_ACUM();
    }
    public void MP(SCCGWA SCCGWA, AICOCKVH AICOCKVH) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.AICOCKVH = AICOCKVH;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "UUUUUUUU");
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "OOOOOOOO");
        CEP.TRC(SCCGWA, "AIZPCKVH return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
        SCRCWA = (SCRCWAT) GWA_SC_AREA.CWA_AREA_PTR;
        BPCPORUP = (BPCPORUP_DATA_INFO) SCCGWA.COMM_AREA.TR_BR_PTR;
        IBS.init(SCCGWA, AICOCKVH.RC);
        WS_VAWR_FST_FLG = 'Y';
        WS_SUS_FLG = 'N';
        WS_ERR_SUS_FLG = 'N';
        A010_GET_PARM();
        if (pgmRtn) return;
        A020_GET_INPUT_DATA();
        if (pgmRtn) return;
    }
    public void A010_GET_PARM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCQBKPM);
        BPCQBKPM.FUNC = 'B';
        S000_CALL_BPZQBKPM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCQBKPM.CNT);
        IBS.init(SCCGWA, BPCPQBAI);
        BPCPQBAI.DATA_INFO.BNK = SCCGWA.COMM_AREA.TR_BANK;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_BANK);
        S000_CALL_BPZPQBAI();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPQBAI.DATA_INFO.FORWARD_DT);
        CEP.TRC(SCCGWA, BPCPQBAI.DATA_INFO.BACK_DT);
        CEP.TRC(SCCGWA, BPCPQBAI.DATA_INFO.CYC_FLG);
        CEP.TRC(SCCGWA, BPCPQBAI.DATA_INFO.CLOSE_DT);
        WS_OPEN_PERIOD.WS_FORWARD_DT = (short) BPCPQBAI.DATA_INFO.FORWARD_DT;
        WS_OPEN_PERIOD.WS_BACK_DT = (short) BPCPQBAI.DATA_INFO.BACK_DT;
        WS_OPEN_PERIOD.WS_CYC_FLG = BPCPQBAI.DATA_INFO.CYC_FLG;
        WS_OPEN_PERIOD.WS_CLOSE_DT = BPCPQBAI.DATA_INFO.CLOSE_DT;
        if (AICOCKVH.REENTRY_FLG == 'Y') {
        }
    }
    public void A020_GET_INPUT_DATA() throws IOException,SQLException,Exception {
        BPRVWA = (BPRVWA) AICOCKVH.VCH_PTR;
        B021_GET_ITEM_INFO();
        if (pgmRtn) return;
        if ((AICOCKVH.FUNC_CD != 'A' 
            && AICOCKVH.FUNC_CD != 'B' 
            && AICOCKVH.FUNC_CD != 'C')) {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_IPT_FUNC_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B021_GET_ITEM_INFO() throws IOException,SQLException,Exception {
        AICITMB.CNT = BPRVWA.BASIC_AREA.CNT;
        WS_ODE_FLG = 'N';
        CEP.TRC(SCCGWA, BPRVWA.BASIC_AREA.CNT);
        for (WS_J = 1; WS_J <= BPRVWA.BASIC_AREA.CNT; WS_J += 1) {
            if (BPRVWA.BASIC_AREA.ODE_FLG == 'Y') {
                WS_ODE_FLG = 'Y';
            }
            if (AICOCKVH.FUNC_CD == 'B' 
                && (BPRVWA.VCH_AREA.get(WS_J-1).CONTROL.AC_FLG == 'D' 
                || BPRVWA.VCH_AREA.get(WS_J-1).CONTROL.AC_FLG == 'N')) {
                B022_GET_ITEM_1();
                if (pgmRtn) return;
            } else {
                ITEM_DATA = new AICITMA_ITEM_DATA();
                AICITMB.ITEM_DATA.add(ITEM_DATA);
                CEP.TRC(SCCGWA, WS_J);
                CEP.TRC(SCCGWA, BPRVWA.VCH_AREA.get(WS_J-1).PARTB.BOOK_FLG);
                CEP.TRC(SCCGWA, BPRVWA.VCH_AREA.get(WS_J-1).PARTB.ITM);
                WS_INQ_DATA.WS_INQ_BOOK_FLG = BPRVWA.VCH_AREA.get(WS_J-1).PARTB.BOOK_FLG;
                WS_INQ_DATA.WS_INQ_ITM = BPRVWA.VCH_AREA.get(WS_J-1).PARTB.ITM;
                B023_GET_ITEM_2();
                if (pgmRtn) return;
            }
        }
    }
    public void B022_GET_ITEM_1() throws IOException,SQLException,Exception {
        ITEM_DATA.ITM_FND_FLG = 'Y';
        ITEM_DATA.ITM_POST_TYPE = 'R';
        ITEM_DATA.ITM_LOW_LVL_FLG = 'Y';
        ITEM_DATA.ITM_STS = 'A';
        if (BPRVWA.VCH_AREA.get(WS_J-1).CONTROL.AC_FLG == 'D') {
            ITEM_DATA.ITM_CATE = 'B';
            ITEM_DATA.ITM_TYPE = "2";
        } else {
            ITEM_DATA.ITM_CATE = 'I';
            ITEM_DATA.ITM_TYPE = "1";
        }
    }
    public void B023_GET_ITEM_2() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICPQITM);
        AICPQITM.INPUT_DATA.BOOK_FLG = WS_INQ_DATA.WS_INQ_BOOK_FLG;
        AICPQITM.INPUT_DATA.NO = WS_INQ_DATA.WS_INQ_ITM;
        S000_CALL_AIZPQITM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "TEST AIZPCKVH");
        ITEM_DATA.ITM_FND_FLG = 'Y';
        ITEM_DATA.ITM_COA_FLG = AICPQITM.INPUT_DATA.COA_FLG;
        ITEM_DATA.ITM_TYPE = AICPQITM.OUTPUT_DATA.TYPE;
        ITEM_DATA.ITM_CATE = AICPQITM.OUTPUT_DATA.CATE;
        ITEM_DATA.ITM_POST_TYPE = AICPQITM.OUTPUT_DATA.POST_TYPE;
        if (AICPQITM.INPUT_DATA.NO == null) AICPQITM.INPUT_DATA.NO = "";
        JIBS_tmp_int = AICPQITM.INPUT_DATA.NO.length();
        for (int i=0;i<10-JIBS_tmp_int;i++) AICPQITM.INPUT_DATA.NO += " ";
        if (AICPQITM.INPUT_DATA.NO.substring(0, 1).equalsIgnoreCase("8") 
            || AICPQITM.INPUT_DATA.NO.substring(0, 1).equalsIgnoreCase("9")) {
            ITEM_DATA.ITM_POST_TYPE = 'M';
        }
        ITEM_DATA.ITM_AUTO_GEN = AICPQITM.OUTPUT_DATA.AUTO_GEN;
        ITEM_DATA.ITM_LOW_LVL_FLG = AICPQITM.OUTPUT_DATA.LOW_LVL_FLG;
        ITEM_DATA.ITM_FX_REVAL_FLG = AICPQITM.OUTPUT_DATA.FX_REVAL_FLG;
        ITEM_DATA.ITM_GLMST = AICPQITM.OUTPUT_DATA.GLMST;
        ITEM_DATA.ITM_LOOKUP_CD = AICPQITM.OUTPUT_DATA.LOOKUP_CD;
        ITEM_DATA.ITM_SENS_LVL = AICPQITM.OUTPUT_DATA.SENS_LVL;
        ITEM_DATA.ITM_BAL_ZERO_FLG = AICPQITM.OUTPUT_DATA.BAL_ZERO_FLG;
        ITEM_DATA.ITM_BAL_SIGN_FLG = AICPQITM.OUTPUT_DATA.BAL_SIGN_FLG;
        ITEM_DATA.ITM_AUTO_MATCH_FLG = AICPQITM.OUTPUT_DATA.AUTO_MATCH_FLG;
        ITEM_DATA.ITM_AUTH_LVL = AICPQITM.OUTPUT_DATA.AUTH_LVL;
        ITEM_DATA.ITM_STS = AICPQITM.OUTPUT_DATA.STS;
        CEP.TRC(SCCGWA, AICPQITM.OUTPUT_DATA.STS);
        ITEM_DATA.ITM_OPEN_DATE = AICPQITM.OUTPUT_DATA.OPEN_DATE;
        ITEM_DATA.ITM_EFF_DATE = AICPQITM.OUTPUT_DATA.EFF_DATE;
        ITEM_DATA.ITM_REOPEN_DATE = AICPQITM.OUTPUT_DATA.REOPEN_DATE;
        ITEM_DATA.ITM_UPD_DATE = AICPQITM.OUTPUT_DATA.UPD_DATE;
        ITEM_DATA.ITM_DEL_DATE = AICPQITM.OUTPUT_DATA.DEL_DATE;
        ITEM_DATA.ITM_RESET_FLG = AICPQITM.OUTPUT_DATA.RESET_FLG;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        WS_ACCUM_CNT = 0;
        WS_USE_MTH_FLG = 'O';
        CEP.TRC(SCCGWA, BPRVWA.BASIC_AREA.CNT);
        CEP.TRC(SCCGWA, BPRVWA.BASIC_AREA.ODE_FLG);
        for (WS_I = 1; WS_I <= BPRVWA.BASIC_AREA.CNT; WS_I += 1) {
            IBS.init(SCCGWA, AIRAIVX);
            AIRAIVX.VCHT_DATA.AP_MMO = SCCGWA.COMM_AREA.AP_MMO;
            AIRAIVX.VCHT_DATA.TR_TYPE = BPRVWA.BASIC_AREA.TR_TYPE;
            AIRAIVX.VCHT_DATA.ODE_FLG = BPRVWA.BASIC_AREA.ODE_FLG;
            AIRAIVX.VCHT_DATA.ODE_GRP_NO = BPRVWA.BASIC_AREA.ODE_GRP_NO;
            WS_INTF_MODE = BPRVWA.BASIC_AREA.INTF_MODE;
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AP_MMO);
            CEP.TRC(SCCGWA, BPRVWA.BASIC_AREA.TR_TYPE);
            CEP.TRC(SCCGWA, BPRVWA.BASIC_AREA.ODE_FLG);
            CEP.TRC(SCCGWA, BPRVWA.BASIC_AREA.ODE_GRP_NO);
            CEP.TRC(SCCGWA, BPRVWA.BASIC_AREA.INTF_MODE);
            AIRAIVX.VCHT_DATA.PARTB.BOOK_FLG = BPRVWA.VCH_AREA.get(WS_I-1).PARTB.BOOK_FLG;
            AIRAIVX.VCHT_DATA.PARTB.BR = BPRVWA.VCH_AREA.get(WS_I-1).PARTB.BR;
            AIRAIVX.VCHT_DATA.PARTB.ITM = BPRVWA.VCH_AREA.get(WS_I-1).PARTB.ITM;
            AIRAIVX.VCHT_DATA.PARTB.CCY = BPRVWA.VCH_AREA.get(WS_I-1).PARTB.CCY;
            AIRAIVX.VCHT_DATA.PARTB.SIGN = BPRVWA.VCH_AREA.get(WS_I-1).PARTB.SIGN;
            AIRAIVX.VCHT_DATA.PARTB.AMT = BPRVWA.VCH_AREA.get(WS_I-1).PARTB.AMT;
            AIRAIVX.VCHT_DATA.PARTB.VAL_DATE = BPRVWA.VCH_AREA.get(WS_I-1).PARTB.VAL_DATE;
            AIRAIVX.VCHT_DATA.PARTB.CNTR_TYPE = BPRVWA.VCH_AREA.get(WS_I-1).PARTB.CNTR_TYPE;
            CEP.TRC(SCCGWA, "DHM");
            CEP.TRC(SCCGWA, BPRVWA.VCH_AREA.get(WS_I-1).PARTB.BOOK_FLG);
            CEP.TRC(SCCGWA, BPRVWA.VCH_AREA.get(WS_I-1).PARTB.BR);
            CEP.TRC(SCCGWA, BPRVWA.VCH_AREA.get(WS_I-1).PARTB.ITM);
            CEP.TRC(SCCGWA, BPRVWA.VCH_AREA.get(WS_I-1).PARTB.CCY);
            CEP.TRC(SCCGWA, BPRVWA.VCH_AREA.get(WS_I-1).PARTB.SIGN);
            CEP.TRC(SCCGWA, BPRVWA.VCH_AREA.get(WS_I-1).PARTB.AMT);
            CEP.TRC(SCCGWA, BPRVWA.VCH_AREA.get(WS_I-1).PARTB.VAL_DATE);
            CEP.TRC(SCCGWA, BPRVWA.VCH_AREA.get(WS_I-1).PARTB.EFF_DAYS);
            AIRAIVX.VCHT_DATA.PARTB.SUSPENSE_FLG = BPRVWA.VCH_AREA.get(WS_I-1).PARTB.SUSPENSE_FLG;
            AIRAIVX.VCHT_DATA.PARTB.SUSPENSE_RSN = BPRVWA.VCH_AREA.get(WS_I-1).PARTB.SUSPENSE_RSN;
            AIRAIVX.VCHT_DATA.PARTB.EFF_DAYS = BPRVWA.VCH_AREA.get(WS_I-1).PARTB.EFF_DAYS;
            IBS.CLONE(SCCGWA, AICITMB.ITEM_DATA.get(WS_I-1), AIRAIVX.ITEM_DATA);
            WS_VCH_SET_END_FLG = 'N';
            WS_VAL_CHK_FLG = 'Y';
            WS_VAL_CHK_ERR_FLG = 'N';
            B900_VCH_PROC();
            if (pgmRtn) return;
            VCH_AREA = BPRVWA.VCH_AREA.get(WS_I-1);
            VCH_AREA.PARTB.BOOK_FLG = AIRAIVX.VCHT_DATA.PARTB.BOOK_FLG;
            BPRVWA.VCH_AREA.set(WS_I-1, VCH_AREA);
            CEP.TRC(SCCGWA, "LWJ");
            CEP.TRC(SCCGWA, AIRAIVX.VCHT_DATA.PARTB.BR);
            VCH_AREA = BPRVWA.VCH_AREA.get(WS_I-1);
            VCH_AREA.PARTB.BR = AIRAIVX.VCHT_DATA.PARTB.BR;
            BPRVWA.VCH_AREA.set(WS_I-1, VCH_AREA);
            VCH_AREA = BPRVWA.VCH_AREA.get(WS_I-1);
            VCH_AREA.PARTB.ITM = AIRAIVX.VCHT_DATA.PARTB.ITM;
            BPRVWA.VCH_AREA.set(WS_I-1, VCH_AREA);
            VCH_AREA = BPRVWA.VCH_AREA.get(WS_I-1);
            VCH_AREA.PARTB.CCY = AIRAIVX.VCHT_DATA.PARTB.CCY;
            BPRVWA.VCH_AREA.set(WS_I-1, VCH_AREA);
            VCH_AREA = BPRVWA.VCH_AREA.get(WS_I-1);
            VCH_AREA.PARTB.SIGN = AIRAIVX.VCHT_DATA.PARTB.SIGN;
            BPRVWA.VCH_AREA.set(WS_I-1, VCH_AREA);
            VCH_AREA = BPRVWA.VCH_AREA.get(WS_I-1);
            VCH_AREA.PARTB.AMT = AIRAIVX.VCHT_DATA.PARTB.AMT;
            BPRVWA.VCH_AREA.set(WS_I-1, VCH_AREA);
            VCH_AREA = BPRVWA.VCH_AREA.get(WS_I-1);
            VCH_AREA.PARTB.VAL_DATE = AIRAIVX.VCHT_DATA.PARTB.VAL_DATE;
            BPRVWA.VCH_AREA.set(WS_I-1, VCH_AREA);
            VCH_AREA = BPRVWA.VCH_AREA.get(WS_I-1);
            VCH_AREA.PARTB.EFF_DAYS = AIRAIVX.VCHT_DATA.PARTB.EFF_DAYS;
            BPRVWA.VCH_AREA.set(WS_I-1, VCH_AREA);
            VCH_AREA = BPRVWA.VCH_AREA.get(WS_I-1);
            VCH_AREA.PARTB.SUSPENSE_FLG = AIRAIVX.VCHT_DATA.PARTB.SUSPENSE_FLG;
            BPRVWA.VCH_AREA.set(WS_I-1, VCH_AREA);
            VCH_AREA = BPRVWA.VCH_AREA.get(WS_I-1);
            VCH_AREA.PARTB.SUSPENSE_RSN = AIRAIVX.VCHT_DATA.PARTB.SUSPENSE_RSN;
            BPRVWA.VCH_AREA.set(WS_I-1, VCH_AREA);
            if (AIRAIVX.VCHT_DATA.PARTB.SUSPENSE_FLG == 'S') {
                VCH_AREA = BPRVWA.VCH_AREA.get(WS_I-1);
                VCH_AREA.CONTROL.AC_FLG = 'I';
                BPRVWA.VCH_AREA.set(WS_I-1, VCH_AREA);
            }
        }
        WS_VCH_SET_END_FLG = 'Y';
        WS_VAL_CHK_FLG = 'N';
        B900_VCH_PROC();
        if (pgmRtn) return;
        if (AICOCKVH.FUNC_CD == 'A') {
            AICITMA = (AICITMA) AICOCKVH.ITM_PTR;
            AICITMA.CNT = AICITMB.CNT;
            ITEM_DATA = new AICITMA_ITEM_DATA();
            AICITMA.ITEM_DATA.add(ITEM_DATA);
            IBS.CLONE(SCCGWA, AICITMB, AICITMA);
        }
    }
    public void R000_VAL_CHK_RESULT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_VAL_CHK_FLG);
        CEP.TRC(SCCGWA, WS_COMM_ERR_MSG);
        CEP.TRC(SCCGWA, WS_VAL_CHK_ERR_FLG);
        CEP.TRC(SCCGWA, K_SEPARATE_FLG);
        CEP.TRC(SCCGWA, WS_BUSINESS_MSG_LVL);
        if (WS_VAL_CHK_ERR_FLG == 'Y' 
            && WS_COMM_ERR_MSG.trim().length() > 0 
            && SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            if (AICOCKVH.FUNC_CD == 'B' 
                || AIRAIVX.VCHT_DATA.ODE_FLG == 'Y') {
                CEP.TRC(SCCGWA, "ODE");
                CEP.ERR(SCCGWA, WS_COMM_ERR_MSG);
            } else {
                CEP.TRC(SCCGWA, "NOT ODE");
                if (K_SEPARATE_FLG == 'N' 
                    || WS_BUSINESS_MSG_LVL == 'E') {
                    CEP.ERR(SCCGWA, WS_COMM_ERR_MSG);
                }
            }
        }
    }
    public void R000_NOT_TODAY_VCH() throws IOException,SQLException,Exception {
    }
    public void R000_BAL_CHK_SUS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRVWA.BASIC_AREA.INTF_MODE);
        CEP.TRC(SCCGWA, AICOCKVH.REENTRY_FLG);
        CEP.TRC(SCCGWA, AICOCKVH.FUNC_CD);
        CEP.TRC(SCCGWA, WS_ODE_FLG);
        CEP.TRC(SCCGWA, WS_TACCUM.WS_TACCUM_KEY.WS_TACCUM_EFFD);
        if (AICOCKVH.REENTRY_FLG != 'Y' 
            && AICOCKVH.FUNC_CD == 'A' 
            && WS_ODE_FLG == 'N' 
            && WS_TACCUM.WS_TACCUM_KEY.WS_TACCUM_EFFD <= 0) {
            CEP.TRC(SCCGWA, WS_TACCUM.WS_TACCUM_AMT);
            if (WS_TACCUM.WS_TACCUM_AMT > 0) {
                WS_DRCR_FLG = 'D';
            } else {
                WS_DRCR_FLG = 'C';
            }
            CEP.TRC(SCCGWA, WS_DRCR_FLG);
            R000_GET_SUS_AC();
            if (pgmRtn) return;
            IBS.init(SCCGWA, BPCOVAWR);
            if (WS_VAWR_FST_FLG == 'Y') {
                BPCOVAWR.FST_FLG = 'Y';
                WS_VAWR_FST_FLG = 'N';
            } else {
                BPCOVAWR.FST_FLG = 'N';
            }
            BPCOVAWR.PARTB.BOOK_FLG = WS_TACCUM.WS_TACCUM_KEY.WS_TACCUM_BOOK;
            if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") 
                || BPCPORUP.ATTR != '2') {
                BPCOVAWR.PARTB.BR = BPRVWA.VCH_AREA.get(1-1).PARTB.BR;
            } else {
                BPCOVAWR.PARTB.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            }
            WS_CHK_DATA.WS_CHK_BOOK_FLG = WS_TACCUM.WS_TACCUM_KEY.WS_TACCUM_BOOK;
            WS_CHK_DATA.WS_CHK_ITM = WS_SUS_INFO_USE.WS_SUS_ITM;
            BPCOVAWR.PARTB.ITM = WS_SUS_INFO_USE.WS_SUS_ITM;
            BPCOVAWR.PARTB.MIB_NO = WS_SUS_INFO_USE.WS_SUS_AC;
            BPCOVAWR.CONTROL.AC_FLG = 'I';
            BPCOVAWR.PARTB.CCY = WS_TACCUM.WS_TACCUM_KEY.WS_TACCUM_CCY;
            if (WS_DRCR_FLG == 'D') {
                BPCOVAWR.PARTB.SIGN = 'D';
                BPCOVAWR.PARTB.AMT = WS_TACCUM.WS_TACCUM_AMT;
            } else {
                BPCOVAWR.PARTB.SIGN = 'C';
                BPCOVAWR.PARTB.AMT = WS_TACCUM.WS_TACCUM_AMT * ( -1 );
            }
            BPCOVAWR.PARTB.RED_FLG = 'B';
            BPCOVAWR.PARTB.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            BPCOVAWR.PARTB.EFF_DAYS = WS_TACCUM.WS_TACCUM_KEY.WS_TACCUM_EFFD;
            BPCOVAWR.PARTB.SUSPENSE_FLG = 'B';
            BPCOVAWR.PARTB.SUSPENSE_RSN = "NOT BAL SUSPENSE ENTRY";
            if (WS_ERR_SUS_FLG != 'Y') {
                if (!(SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BSP") 
                    || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT"))) {
                    WS_ERR_MSG = AICMSG_ERROR_MSG.AI_VCH_NOT_BAL;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
            S000_CALL_BPZPVAWR();
            if (pgmRtn) return;
            WS_INQ_DATA.WS_INQ_BOOK_FLG = WS_TACCUM.WS_TACCUM_KEY.WS_TACCUM_BOOK;
            WS_INQ_DATA.WS_INQ_ITM = WS_SUS_INFO_USE.WS_SUS_ITM;
            WS_J = BPRVWA.BASIC_AREA.CNT;
            AICITMB.CNT = BPRVWA.BASIC_AREA.CNT;
            ITEM_DATA = new AICITMA_ITEM_DATA();
            AICITMB.ITEM_DATA.add(ITEM_DATA);
            B023_GET_ITEM_2();
            if (pgmRtn) return;
            IBS.CLONE(SCCGWA, AICITMB.ITEM_DATA.get(WS_J-1), AIRAIVX.ITEM_DATA);
            IBS.CLONE(SCCGWA, BPCOVAWR.PARTB, AIRAIVX.VCHT_DATA.PARTB);
        } else {
            WS_ERR_MSG = WS_COMM_ERR_MSG;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void R000_INR_BR_SUS() throws IOException,SQLException,Exception {
        if (AICOCKVH.FUNC_CD == 'B' 
            || AICOCKVH.FUNC_CD == 'C') {
            if (BPRVWA.BASIC_AREA.CNT == K_OCCURS_SET_MAX_ONL) {
                WS_ERR_MSG = AICMSG_ERROR_MSG.AI_EXCEED_MAX_VCH;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            } else {
                VCH_AREA = new BPRVWA_VCH_AREA();
                BPRVWA.VCH_AREA.add(VCH_AREA);
                BPRVWA.BASIC_AREA.CNT = BPRVWA.BASIC_AREA.CNT + 1;
                IBS.init(SCCGWA, VCH_AREA);
            }
        } else {
            IBS.init(SCCGWA, BPCOVAWR);
            if (WS_VAWR_FST_FLG == 'Y') {
                BPCOVAWR.FST_FLG = 'Y';
                WS_VAWR_FST_FLG = 'N';
            } else {
                BPCOVAWR.FST_FLG = 'N';
            }
            BPCOVAWR.PARTB.BOOK_FLG = WS_ACCUM[WS_COUNTER.WS_IDX_K-1].WS_ACCUM_KEY.WS_ACCUM_BOOK;
            BPCOVAWR.PARTB.BR = WS_ACCUM[WS_COUNTER.WS_IDX_K-1].WS_ACCUM_KEY.WS_ACCUM_BR;
            WS_CHK_DATA.WS_CHK_BOOK_FLG = WS_ACCUM[WS_COUNTER.WS_IDX_K-1].WS_ACCUM_KEY.WS_ACCUM_BOOK;
            WS_CHK_DATA.WS_CHK_ITM = WS_CRS_SUS_INFO.WS_CRS_SUS_R;
            BPCOVAWR.PARTB.ITM = WS_CRS_SUS_INFO.WS_CRS_SUS_R;
            BPCOVAWR.PARTB.MIB_NO = WS_CRS_SUS_INFO.WS_CRS_IA_R;
            CEP.TRC(SCCGWA, BPCOVAWR.PARTB.MIB_NO);
            if (BPCOVAWR.PARTB.MIB_NO.trim().length() > 0) {
                BPCOVAWR.CONTROL.AC_FLG = 'I';
            }
            BPCOVAWR.PARTB.CCY = WS_ACCUM[WS_COUNTER.WS_IDX_K-1].WS_ACCUM_KEY.WS_ACCUM_CCY;
            CEP.TRC(SCCGWA, WS_COUNTER.WS_IDX_K);
            CEP.TRC(SCCGWA, WS_ACCUM[WS_COUNTER.WS_IDX_K-1].WS_ACCUM_AMT);
            if (WS_ACCUM[WS_COUNTER.WS_IDX_K-1].WS_ACCUM_AMT > 0) {
                BPCOVAWR.PARTB.SIGN = 'D';
                BPCOVAWR.PARTB.AMT = WS_ACCUM[WS_COUNTER.WS_IDX_K-1].WS_ACCUM_AMT;
                CEP.TRC(SCCGWA, "SIGN-DR");
            } else {
                BPCOVAWR.PARTB.SIGN = 'C';
                BPCOVAWR.PARTB.AMT = WS_ACCUM[WS_COUNTER.WS_IDX_K-1].WS_ACCUM_AMT * -1;
                CEP.TRC(SCCGWA, "SIGN-CR");
            }
            if (BPRVWA.BASIC_AREA.ODE_FLG == 'Y') {
                CEP.TRC(SCCGWA, "160104-005");
                CEP.TRC(SCCGWA, BPRVWA.VCH_AREA.get(1-1).PARTB.TLR_ID);
                if (BPRVWA.VCH_AREA.get(1-1).PARTB.TLR_ID.trim().length() > 0) {
                    BPCOVAWR.PARTB.TLR_ID = BPRVWA.VCH_AREA.get(1-1).PARTB.TLR_ID;
                }
            }
            BPCOVAWR.PARTB.RED_FLG = 'B';
            BPCOVAWR.PARTB.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            IBS.init(SCCGWA, BPCPRMR);
            IBS.init(SCCGWA, BPRTRT);
            BPRTRT.KEY.CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
            BPRTRT.KEY.TYP = "TRT";
            BPCPRMR.DAT_PTR = BPRTRT;
            S000_CALL_BPZPRMR();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPRTRT.DESC);
            BPCOVAWR.PARTB.POST_NARR = BPRTRT.DESC;
            BPCOVAWR.PARTB.DESC = BPRTRT.DESC;
            BPCOVAWR.PARTB.EFF_DAYS = WS_ACCUM[WS_COUNTER.WS_IDX_K-1].WS_ACCUM_KEY.WS_ACCUM_EFFD;
            S000_CALL_BPZPVAWR();
            if (pgmRtn) return;
            WS_INQ_DATA.WS_INQ_BOOK_FLG = WS_ACCUM[WS_COUNTER.WS_IDX_K-1].WS_ACCUM_KEY.WS_ACCUM_BOOK;
            WS_INQ_DATA.WS_INQ_ITM = WS_CRS_SUS_INFO.WS_CRS_SUS_R;
            WS_J = BPRVWA.BASIC_AREA.CNT;
            AICITMB.CNT = BPRVWA.BASIC_AREA.CNT;
            ITEM_DATA = new AICITMA_ITEM_DATA();
            AICITMB.ITEM_DATA.add(ITEM_DATA);
            B023_GET_ITEM_2();
            if (pgmRtn) return;
        }
    }
    public void R000_CHK_ITM_POSTING() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICPQITM);
        AICPQITM.INPUT_DATA.BOOK_FLG = WS_CHK_DATA.WS_CHK_BOOK_FLG;
        AICPQITM.INPUT_DATA.NO = WS_CHK_DATA.WS_CHK_ITM;
        S000_CALL_AIZPQITM();
        if (pgmRtn) return;
        if (AICPQITM.OUTPUT_DATA.STS != 'A') {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_COA_NOT_ACTIVE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (AICPQITM.OUTPUT_DATA.LOW_LVL_FLG != 'Y') {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_COA_NO_LOW_LVL;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void R000_GET_SUS_AC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMR);
        IBS.init(SCCGWA, AIRPAI7);
        CEP.TRC(SCCGWA, WS_ITM_RM_FLG);
        AIRPAI7.KEY.TYP = "PAI07";
        AIRPAI7.KEY.REDEFINES6.AC_TYP = "1";
        AIRPAI7.KEY.REDEFINES6.GL_BOOK = AIRAIVX.VCHT_DATA.PARTB.BOOK_FLG;
        AIRPAI7.KEY.CD = IBS.CLS2CPY(SCCGWA, AIRPAI7.KEY.REDEFINES6);
        AIRPAI7.KEY.REDEFINES6.BUSI_KND = AIRAIVX.VCHT_DATA.AP_MMO;
        AIRPAI7.KEY.CD = IBS.CLS2CPY(SCCGWA, AIRPAI7.KEY.REDEFINES6);
        BPCPRMR.DAT_PTR = AIRPAI7;
        S000_CALL_BPZPRMR();
        if (pgmRtn) return;
        if (WS_ITM_RM_FLG == 'M') {
            if (WS_DRCR_FLG == 'D') {
                WS_SUS_INFO_USE.WS_SUS_ITM = AIRPAI7.DATA_TXT.DATA_INF.ITM_M_D;
                WS_SUS_INFO_USE.WS_SUS_SEQ = AIRPAI7.DATA_TXT.DATA_INF.SEQ_M_D;
            } else {
                WS_SUS_INFO_USE.WS_SUS_ITM = AIRPAI7.DATA_TXT.DATA_INF.ITM_M_C;
                WS_SUS_INFO_USE.WS_SUS_SEQ = AIRPAI7.DATA_TXT.DATA_INF.SEQ_M_C;
            }
        } else {
            if (WS_DRCR_FLG == 'D') {
                WS_SUS_INFO_USE.WS_SUS_ITM = AIRPAI7.DATA_TXT.DATA_INF.ITM_R_D;
                WS_SUS_INFO_USE.WS_SUS_SEQ = AIRPAI7.DATA_TXT.DATA_INF.SEQ_R_D;
            } else {
                WS_SUS_INFO_USE.WS_SUS_ITM = AIRPAI7.DATA_TXT.DATA_INF.ITM_R_C;
                WS_SUS_INFO_USE.WS_SUS_SEQ = AIRPAI7.DATA_TXT.DATA_INF.SEQ_R_C;
            }
        }
        if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") 
            || BPCPORUP.ATTR != '2') {
            WS_INTERNAL_AC.WS_INTERNAL_BR = BPRVWA.VCH_AREA.get(1-1).PARTB.BR;
        } else {
            WS_INTERNAL_AC.WS_INTERNAL_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        }
        WS_INTERNAL_AC.WS_INTERNAL_ITM = WS_SUS_INFO_USE.WS_SUS_ITM;
        WS_INTERNAL_AC.WS_INTERNAL_SEQ = WS_SUS_INFO_USE.WS_SUS_SEQ;
        WS_INTERNAL_AC.WS_INTERNAL_CCY = WS_TACCUM.WS_TACCUM_KEY.WS_TACCUM_CCY;
        WS_SUS_INFO_USE.WS_SUS_AC = IBS.CLS2CPY(SCCGWA, WS_INTERNAL_AC);
    }
    public void S000_CALL_AIZPQMIB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-MIB", AICPQMIB);
    }
    public void S000_CALL_AIZPQITM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-ITM", AICPQITM);
        if (AICPQITM.OUTPUT_DATA.STS != 'A' 
            && AICPQITM.OUTPUT_DATA.STS != 'S') {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_COA_NOT_ACTIVE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (AICPQITM.OUTPUT_DATA.LOW_LVL_FLG != 'Y') {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_COA_NO_LOW_LVL;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (WS_ERR_SUS_FLG != 'Y') {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AICPQITM.RC);
            if (JIBS_tmp_str[0].equalsIgnoreCase(AICMSG_ERROR_MSG.AI_READ_AITITM_NOTFND) 
                && (!(SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") 
                || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")))) {
                WS_ERR_INFO = "ITM = " + AICPQITM.INPUT_DATA.NO;
                CEP.TRC(SCCGWA, WS_ERR_INFO);
                WS_ERR_MSG = AICMSG_ERROR_MSG.AI_READ_AITITM_NOTFND;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (AICPQITM.RC.RTNCODE != 0) {
            WS_ERR_INFO = "ITM = " + AICPQITM.INPUT_DATA.NO;
            CEP.TRC(SCCGWA, "BOBO");
            CEP.TRC(SCCGWA, WS_ERR_INFO);
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICPQITM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_AIZPQITM_PASS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-ITM", AICPQITM);
        if (WS_ERR_SUS_FLG != 'Y') {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AICPQITM.RC);
            if (JIBS_tmp_str[0].equalsIgnoreCase(AICMSG_ERROR_MSG.AI_READ_AITITM_NOTFND) 
                && (!(SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") 
                || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")))) {
                WS_ERR_INFO = "ITM = " + AICPQITM.INPUT_DATA.NO;
                CEP.TRC(SCCGWA, WS_ERR_INFO);
                WS_ERR_MSG = AICMSG_ERROR_MSG.AI_READ_AITITM_NOTFND;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, AICPQITM.RC);
        if (AICPQITM.RC.RTNCODE != 0 
            && !JIBS_tmp_str[1].equalsIgnoreCase(AICMSG_ERROR_MSG.AI_READ_AITITM_NOTFND)) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICPQITM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZQBKPM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-Q-MAINT-AMBKP", BPCQBKPM);
        if (BPCQBKPM.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCQBKPM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZQCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-INQUIRE-CCY", BPCQCCY);
        if (BPCQCCY.RC.RTNCODE == 0) {
            WS_CCY_FND_FLG = 'Y';
        }
    }
    public void S000_CALL_BPZPQBAI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-BKAI", BPCPQBAI);
        if (BPCPQBAI.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQBAI.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_AIZPCKOP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-CHK-OPEN-PERIOD", AICOCKOP);
        if (AICOCKOP.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICOCKOP.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPVAWR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-VWA-WRITE", BPCOVAWR);
        if (BPCOVAWR.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCOVAWR.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ", BPCPRMR);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_AIZUPRVS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-U-PROC-RVS", AICUPRVS);
    }
    public void S000_CALL_SCZIMSG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "SC-MSG-INQ", SCCIMSG);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        if (AICOCKVH.FUNC_CD == 'C') {
            IBS.CPY2CLS(SCCGWA, WS_ERR_MSG, AICOCKVH.RC);
        } else {
            CEP.ERR(SCCGWA, WS_ERR_MSG, WS_ERR_INFO);
        }
    }
    public void S000_ERR_MSG_PROC_INFO() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_ERR_INFO);
    }
    public void B900_VCH_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_VAL_CHK_FLG);
        if (WS_VCH_SET_END_FLG == 'Y') {
            B930_ACCUM_SORT();
            if (pgmRtn) return;
            WS_ITM_RM_FLG = ' ';
            B940_BALANCE_CHK();
            if (pgmRtn) return;
            if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                B980_CRS_VIL_PROCESS();
                if (pgmRtn) return;
            }
            if (WS_USE_MTH_FLG == 'O') {
            }
            WS_ACCUM_CNT = 0;
        }
        if (WS_VAL_CHK_FLG == 'Y') {
            B910_VALID_CHK();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, WS_VAL_CHK_FLG);
            CEP.TRC(SCCGWA, WS_COMM_ERR_MSG);
            CEP.TRC(SCCGWA, WS_VAL_CHK_ERR_FLG);
            CEP.TRC(SCCGWA, "DEBUG !!!!!!!!");
            CEP.TRC(SCCGWA, AIRAIVX.ITEM_DATA.ITM_TYPE);
            CEP.TRC(SCCGWA, AIRAIVX.VCHT_DATA.PARTB.ITM);
            CEP.TRC(SCCGWA, AIRAIVX.ITEM_DATA.ITM_FND_FLG);
            CEP.TRC(SCCGWA, AIRAIVX.ITEM_DATA.ITM_POST_TYPE);
            if (AIRAIVX.VCHT_DATA.PARTB.ITM.trim().length() > 0) {
                if (AIRAIVX.ITEM_DATA.ITM_POST_TYPE == ' ') {
                    if (AIRAIVX.VCHT_DATA.PARTB.ITM == null) AIRAIVX.VCHT_DATA.PARTB.ITM = "";
                    JIBS_tmp_int = AIRAIVX.VCHT_DATA.PARTB.ITM.length();
                    for (int i=0;i<10-JIBS_tmp_int;i++) AIRAIVX.VCHT_DATA.PARTB.ITM += " ";
                    if (AIRAIVX.VCHT_DATA.PARTB.ITM.substring(0, 1).equalsIgnoreCase("8") 
                        || AIRAIVX.VCHT_DATA.PARTB.ITM.substring(0, 1).equalsIgnoreCase("9")) {
                        AIRAIVX.ITEM_DATA.ITM_POST_TYPE = 'M';
                    } else {
                        AIRAIVX.ITEM_DATA.ITM_POST_TYPE = 'R';
                    }
                }
            } else {
                WS_BUSINESS_MSG_LVL = 'E';
                WS_VAL_CHK_ERR_FLG = 'Y';
                WS_COMM_ERR_MSG = AICMSG_ERROR_MSG.AI_COA_NO_MUST_INPUT;
            }
            if (AIRAIVX.ITEM_DATA.ITM_POST_TYPE == 'R') {
                B920_ACCUM_VCH();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, WS_VAL_CHK_FLG);
            CEP.TRC(SCCGWA, WS_COMM_ERR_MSG);
            CEP.TRC(SCCGWA, WS_VAL_CHK_ERR_FLG);
        }
        CEP.TRC(SCCGWA, "VAL CHK RESULT");
        R000_VAL_CHK_RESULT();
        if (pgmRtn) return;
    }
    public void B910_VALID_CHK() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, AIRAIVX.VCHT_DATA.PARTB.BOOK_FLG);
        if (WS_VAL_CHK_ERR_FLG == 'N') {
            if (AIRAIVX.VCHT_DATA.PARTB.BOOK_FLG.trim().length() == 0) {
                WS_BOOK_FND_FLG = 'N';
            } else {
                WS_BOOK_FLG = AIRAIVX.VCHT_DATA.PARTB.BOOK_FLG;
                B911_CHK_BOOK_FLG();
                if (pgmRtn) return;
            }
            if (WS_BOOK_FND_FLG == 'N') {
                CEP.TRC(SCCGWA, "BOOK NOT FOUND");
                WS_VAL_CHK_ERR_FLG = 'Y';
                WS_BUSINESS_MSG_LVL = 'E';
                WS_COMM_ERR_MSG = AICMSG_ERROR_MSG.GL_BOOK_NOT_VAL;
            }
        }
        CEP.TRC(SCCGWA, AIRAIVX.VCHT_DATA.PARTB.BR);
        CEP.TRC(SCCGWA, "TEST BR");
        if (WS_VAL_CHK_ERR_FLG == 'N' 
            && ((!SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) 
            || SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP"))) {
            CEP.TRC(SCCGWA, AIRAIVX.VCHT_DATA.PARTB.BR);
            if (AIRAIVX.VCHT_DATA.PARTB.BR <= 0) {
                WS_BR_FND_FLG = 'N';
            } else {
                IBS.init(SCCGWA, BPCPQORG);
                BPCPQORG.BR = AIRAIVX.VCHT_DATA.PARTB.BR;
                S000_CALL_BPZPQORG();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, BPCPQORG.ATTR);
                if (BPCPQORG.ATTR != '2' 
                    && BPCPQORG.ATTR != '3') {
                    CEP.TRC(SCCGWA, "ATTR");
                    WS_VAL_CHK_ERR_FLG = 'Y';
                    WS_BUSINESS_MSG_LVL = 'E';
                    WS_COMM_ERR_MSG = AICMSG_ERROR_MSG.AI_NOT_BOOK_BR;
                }
            }
            if (WS_BR_FND_FLG == 'N') {
                WS_VAL_CHK_ERR_FLG = 'Y';
                WS_BUSINESS_MSG_LVL = 'E';
                WS_COMM_ERR_MSG = AICMSG_ERROR_MSG.TR_BR_NOT_FND;
            }
        }
        CEP.TRC(SCCGWA, AIRAIVX.VCHT_DATA.PARTB.CCY);
        if (WS_VAL_CHK_ERR_FLG == 'N') {
            if (AIRAIVX.VCHT_DATA.PARTB.CCY.trim().length() == 0) {
                WS_CCY_FND_FLG = 'N';
            }
            if (WS_CCY_FND_FLG == 'N') {
                CEP.TRC(SCCGWA, "CCY INVALID");
                WS_VAL_CHK_ERR_FLG = 'Y';
                WS_BUSINESS_MSG_LVL = 'E';
                WS_COMM_ERR_MSG = AICMSG_ERROR_MSG.CCY_NOT_FND;
            }
        }
        CEP.TRC(SCCGWA, AIRAIVX.VCHT_DATA.PARTB.AMT);
        if (WS_VAL_CHK_ERR_FLG == 'N') {
            if (!IBS.isNumeric(AIRAIVX.VCHT_DATA.PARTB.AMT+"") 
                || AIRAIVX.VCHT_DATA.PARTB.AMT == 0) {
                CEP.TRC(SCCGWA, "AMT INVALID");
                WS_VAL_CHK_ERR_FLG = 'Y';
                WS_BUSINESS_MSG_LVL = 'E';
                WS_COMM_ERR_MSG = AICMSG_ERROR_MSG.AI_AMT_NOT_VAL;
            }
        }
        CEP.TRC(SCCGWA, AIRAIVX.VCHT_DATA.PARTB.SIGN);
        if (WS_VAL_CHK_ERR_FLG == 'N') {
            WS_SIGN_DRCR_FLG = AIRAIVX.VCHT_DATA.PARTB.SIGN;
            if ((WS_SIGN_DRCR_FLG != 'D' 
                && WS_SIGN_DRCR_FLG != 'C')) {
                CEP.TRC(SCCGWA, "DRCR FLAG INVALID");
                WS_VAL_CHK_ERR_FLG = 'Y';
                WS_BUSINESS_MSG_LVL = 'E';
                WS_COMM_ERR_MSG = AICMSG_ERROR_MSG.AI_DCFLG_NOT_VAL;
            }
        }
        CEP.TRC(SCCGWA, AIRAIVX.VCHT_DATA.PARTB.EFF_DAYS);
        if (WS_VAL_CHK_ERR_FLG == 'N') {
            CEP.TRC(SCCGWA, AIRAIVX.VCHT_DATA.PARTB.EFF_DAYS);
            if (!IBS.isNumeric(AIRAIVX.VCHT_DATA.PARTB.EFF_DAYS+"")) {
                CEP.TRC(SCCGWA, "EFFECTIVE DAY INVALID");
                WS_VAL_CHK_ERR_FLG = 'Y';
                WS_BUSINESS_MSG_LVL = 'E';
                WS_COMM_ERR_MSG = AICMSG_ERROR_MSG.AI_EFF_DAYS_ERR;
            }
            if (AIRAIVX.VCHT_DATA.PARTB.EFF_DAYS < 0 
                && WS_USE_MTH_FLG == 'B') {
                AIRAIVX.VCHT_DATA.PARTB.EFF_DAYS = 0;
            }
        }
        if (WS_VAL_CHK_ERR_FLG == 'N') {
            if (AIRAIVX.ITEM_DATA.ITM_FND_FLG != 'Y') {
                CEP.TRC(SCCGWA, "ITEM NOT FOUND");
                CEP.TRC(SCCGWA, AIRAIVX.VCHT_DATA.PARTB.ITM);
                CEP.TRC(SCCGWA, AIRAIVX.VCHT_DATA.PARTB.GLMST);
                CEP.TRC(SCCGWA, AIRAIVX.VCHT_DATA.PARTB.EVENT_CODE);
                WS_VAL_CHK_ERR_FLG = 'Y';
                WS_BUSINESS_MSG_LVL = 'E';
                WS_COMM_ERR_MSG = AICMSG_ERROR_MSG.AI_COA_NO_INFO_NOTFND;
            }
        }
        if (WS_VAL_CHK_ERR_FLG == 'N') {
            CEP.TRC(SCCGWA, AIRAIVX.ITEM_DATA.ITM_STS);
            if (AIRAIVX.ITEM_DATA.ITM_STS == 'P' 
                || AIRAIVX.ITEM_DATA.ITM_STS == 'C' 
                || AIRAIVX.ITEM_DATA.ITM_STS == 'H') {
                CEP.TRC(SCCGWA, "ITM STATUS UNNORMAL");
                WS_VAL_CHK_ERR_FLG = 'Y';
                WS_BUSINESS_MSG_LVL = 'W';
                WS_COMM_ERR_MSG = AICMSG_ERROR_MSG.AI_COA_STS_ERR;
            }
        }
    }
    public void B911_CHK_BOOK_FLG() throws IOException,SQLException,Exception {
        WS_BOOK_FND_FLG = 'N';
        CEP.TRC(SCCGWA, WS_BOOK_FLG);
        CEP.TRC(SCCGWA, BPCQBKPM.CNT);
        for (WS_COUNTER.WS_IDX_L = 1; WS_COUNTER.WS_IDX_L <= BPCQBKPM.CNT 
            && WS_BOOK_FND_FLG != 'Y'; WS_COUNTER.WS_IDX_L += 1) {
            CEP.TRC(SCCGWA, BPCQBKPM.DATA[WS_COUNTER.WS_IDX_L-1].BOOK_FLG);
            CEP.TRC(SCCGWA, BPCQBKPM.DATA[WS_COUNTER.WS_IDX_L-1].STS);
            if (BPCQBKPM.DATA[WS_COUNTER.WS_IDX_L-1].BOOK_FLG.equalsIgnoreCase(WS_BOOK_FLG) 
                && BPCQBKPM.DATA[WS_COUNTER.WS_IDX_L-1].STS == 'A') {
                WS_BOOK_FND_FLG = 'Y';
                WS_COUNTER.WS_IDX_M = WS_COUNTER.WS_IDX_L;
                WS_CRS_SUS_INFO.WS_CRS_SUS_R = BPCQBKPM.DATA[WS_COUNTER.WS_IDX_L-1].CRS_BR_ITM;
                WS_CRS_SUS_INFO.WS_CRS_SEQ_R = BPCQBKPM.DATA[WS_COUNTER.WS_IDX_L-1].SEQ4;
                WS_CRS_SUS_INFO.WS_CRS_SUS_M = BPCQBKPM.DATA[WS_COUNTER.WS_IDX_L-1].CRS_BR_ITM_M;
                WS_CRS_SUS_INFO.WS_CRS_SEQ_M = BPCQBKPM.DATA[WS_COUNTER.WS_IDX_L-1].SEQ5;
            }
        }
    }
    public void B999_GET_ITM_AITITUS() throws IOException,SQLException,Exception {
        WS_ITM_CNT = 0;
        WS_ITM = new AIZPCKVH_WS_ITM();
        WS_ITML.add(WS_ITM);
        T000_STARTBR_AITITUS_1();
        if (pgmRtn) return;
        T000_READNEXT_AITITUS();
        if (pgmRtn) return;
        while (WS_FND_AUTH_FLG != 'N') {
            WS_ITM_CNT += 1;
            WS_ITM = new AIZPCKVH_WS_ITM();
            WS_ITML.add(WS_ITM);
            if (WS_ITM_CNT > WS_ITM_MAX_CNT) {
                WS_ERR_MSG = AICMSG_ERROR_MSG.AI_EX_MAX_REC;
                WS_ERR_INFO = "ARRAY-MAX-CNT = " + WS_ITM_MAX_CNT + "ARRAY-CUR-CNT = " + WS_ITM_CNT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            WS_ITM.WS_ITM_NO = AIRITUS.KEY.ITM_NO;
            CEP.TRC(SCCGWA, WS_ITML.get(WS_ITM_CNT-1).WS_ITM_NO);
            T000_READNEXT_AITITUS();
            if (pgmRtn) return;
        }
    }
    public void B920_ACCUM_VCH() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "CHLCHL");
        CEP.TRC(SCCGWA, WS_ACCUM_CNT);
        CEP.TRC(SCCGWA, AIRAIVX.VCHT_DATA.PARTB.EFF_DAYS);
        CEP.TRC(SCCGWA, AIRAIVX.VCHT_DATA.PARTB.BOOK_FLG);
        CEP.TRC(SCCGWA, AIRAIVX.VCHT_DATA.PARTB.CCY);
        CEP.TRC(SCCGWA, AIRAIVX.VCHT_DATA.PARTB.VAL_DATE);
        CEP.TRC(SCCGWA, AIRAIVX.VCHT_DATA.PARTB.BR);
        for (WS_COUNTER.WS_IDX_I = WS_ACCUM_CNT; WS_COUNTER.WS_IDX_I != 0; WS_COUNTER.WS_IDX_I += -1) {
            CEP.TRC(SCCGWA, WS_COUNTER.WS_IDX_I);
            CEP.TRC(SCCGWA, WS_ACCUM_CNT);
            if (WS_ACCUM[WS_COUNTER.WS_IDX_I-1].WS_ACCUM_KEY.WS_ACCUM_EFFD == AIRAIVX.VCHT_DATA.PARTB.EFF_DAYS 
                && WS_ACCUM[WS_COUNTER.WS_IDX_I-1].WS_ACCUM_KEY.WS_ACCUM_BOOK.equalsIgnoreCase(AIRAIVX.VCHT_DATA.PARTB.BOOK_FLG) 
                && WS_ACCUM[WS_COUNTER.WS_IDX_I-1].WS_ACCUM_KEY.WS_ACCUM_CCY.equalsIgnoreCase(AIRAIVX.VCHT_DATA.PARTB.CCY) 
                && WS_ACCUM[WS_COUNTER.WS_IDX_I-1].WS_ACCUM_KEY.WS_ACCUM_BR == AIRAIVX.VCHT_DATA.PARTB.BR) {
                B921_ACCUM_AMT_SUM();
                if (pgmRtn) return;
                WS_COUNTER.WS_IDX_I = 1;
            } else {
                if (WS_COUNTER.WS_IDX_I == 1) {
                    B922_ACCUM_ADD();
                    if (pgmRtn) return;
                    WS_COUNTER.WS_IDX_I = 1;
                }
            }
        }
        if (WS_ACCUM_CNT == 0) {
            B922_ACCUM_ADD();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, WS_ACCUM_CNT);
    }
    public void B921_ACCUM_AMT_SUM() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "FCTST");
        CEP.TRC(SCCGWA, AIRAIVX.VCHT_DATA.PARTB.CNTR_TYPE);
        CEP.TRC(SCCGWA, AIRAIVX.VCHT_DATA.PARTB.SIGN);
        CEP.TRC(SCCGWA, AIRAIVX.VCHT_DATA.PARTB.AMT);
        WS_SIGN_DRCR_FLG = AIRAIVX.VCHT_DATA.PARTB.SIGN;
        if (WS_SIGN_DRCR_FLG == 'D') {
            WS_ACCUM[WS_COUNTER.WS_IDX_I-1].WS_ACCUM_AMT = WS_ACCUM[WS_COUNTER.WS_IDX_I-1].WS_ACCUM_AMT - AIRAIVX.VCHT_DATA.PARTB.AMT;
        } else {
            WS_ACCUM[WS_COUNTER.WS_IDX_I-1].WS_ACCUM_AMT = WS_ACCUM[WS_COUNTER.WS_IDX_I-1].WS_ACCUM_AMT + AIRAIVX.VCHT_DATA.PARTB.AMT;
        }
        CEP.TRC(SCCGWA, WS_ACCUM[WS_COUNTER.WS_IDX_I-1].WS_ACCUM_AMT);
    }
    public void B922_ACCUM_ADD() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        WS_OCCURS_SET_MAX = K_OCCURS_SET_MAX_ONL;
    } else { //FROM #ELSE
        if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP")) {
            WS_OCCURS_SET_MAX = K_OCCURS_SET_MAX_ONL;
        } else {
            WS_OCCURS_SET_MAX = K_OCCURS_SET_MAX_BAT;
        }
    } //FROM #ENDIF
        if (WS_ACCUM_CNT == WS_OCCURS_SET_MAX) {
            CEP.TRC(SCCGWA, "EXCEED MAX VCH CNT");
            WS_VAL_CHK_ERR_FLG = 'Y';
            WS_BUSINESS_MSG_LVL = 'E';
            WS_COMM_ERR_MSG = AICMSG_ERROR_MSG.AI_EXCEED_MAX_VCH;
            CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_EXCEED_MAX_VCH);
        } else {
            WS_ACCUM_CNT = WS_ACCUM_CNT + 1;
            WS_COUNTER.WS_IDX_I = WS_ACCUM_CNT;
            WS_ACCUM[WS_COUNTER.WS_IDX_I-1].WS_ACCUM_KEY.WS_ACCUM_EFFD = (short) AIRAIVX.VCHT_DATA.PARTB.EFF_DAYS;
            WS_ACCUM[WS_COUNTER.WS_IDX_I-1].WS_ACCUM_KEY.WS_ACCUM_BOOK = AIRAIVX.VCHT_DATA.PARTB.BOOK_FLG;
            WS_ACCUM[WS_COUNTER.WS_IDX_I-1].WS_ACCUM_KEY.WS_ACCUM_CCY = AIRAIVX.VCHT_DATA.PARTB.CCY;
            WS_ACCUM[WS_COUNTER.WS_IDX_I-1].WS_ACCUM_KEY.WS_ACCUM_BR = AIRAIVX.VCHT_DATA.PARTB.BR;
            WS_ACCUM[WS_COUNTER.WS_IDX_I-1].WS_ACCUM_AMT = 0;
            WS_ACCUM[WS_COUNTER.WS_IDX_I-1].WS_ACCUM_KEY.WS_ACCUM_SIGN = AIRAIVX.VCHT_DATA.PARTB.SIGN;
            B921_ACCUM_AMT_SUM();
            if (pgmRtn) return;
        }
    }
    public void B930_ACCUM_SORT() throws IOException,SQLException,Exception {
        for (WS_COUNTER.WS_IDX1 = 1; WS_COUNTER.WS_IDX1 <= WS_ACCUM_CNT; WS_COUNTER.WS_IDX1 += 1) {
            WS_COUNTER.WS_IDX2 = WS_ACCUM_CNT - WS_COUNTER.WS_IDX1;
            for (WS_COUNTER.WS_IDX3 = 1; WS_COUNTER.WS_IDX3 <= WS_COUNTER.WS_IDX2; WS_COUNTER.WS_IDX3 += 1) {
                WS_COUNTER.WS_IDX4 = WS_COUNTER.WS_IDX3 + 1;
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_ACCUM[WS_COUNTER.WS_IDX3-1].WS_ACCUM_KEY);
                JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, WS_ACCUM[WS_COUNTER.WS_IDX4-1].WS_ACCUM_KEY);
                if (JIBS_tmp_str[0].compareTo(JIBS_tmp_str[1]) > 0) {
                    JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_ACCUM[WS_COUNTER.WS_IDX4-1]);
                    IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_TACCUM);
                    JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_ACCUM[WS_COUNTER.WS_IDX3-1]);
                    IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_ACCUM[WS_COUNTER.WS_IDX4-1]);
                    JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_TACCUM);
                    IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_ACCUM[WS_COUNTER.WS_IDX3-1]);
                }
            }
        }
    }
    public void B940_BALANCE_CHK() throws IOException,SQLException,Exception {
        WS_ACCUMT_CNT = 0;
        WS_MACCUMT_CNT = 0;
        CEP.TRC(SCCGWA, "***WS-ACCUM-DATA***");
        CEP.TRC(SCCGWA, WS_ACCUM_CNT);
        for (WS_COUNTER.WS_IDX_J = 1; WS_COUNTER.WS_IDX_J <= WS_ACCUM_CNT; WS_COUNTER.WS_IDX_J += 1) {
            CEP.TRC(SCCGWA, WS_COUNTER.WS_IDX_J);
            CEP.TRC(SCCGWA, WS_ACCUM[WS_COUNTER.WS_IDX_J-1].WS_ACCUM_KEY.WS_ACCUM_EFFD);
            CEP.TRC(SCCGWA, WS_ACCUM[WS_COUNTER.WS_IDX_J-1].WS_ACCUM_KEY.WS_ACCUM_BOOK);
            CEP.TRC(SCCGWA, WS_ACCUM[WS_COUNTER.WS_IDX_J-1].WS_ACCUM_KEY.WS_ACCUM_CCY);
            CEP.TRC(SCCGWA, WS_ACCUM[WS_COUNTER.WS_IDX_J-1].WS_ACCUM_KEY.WS_ACCUM_BR);
            CEP.TRC(SCCGWA, WS_ACCUM[WS_COUNTER.WS_IDX_J-1].WS_ACCUM_AMT);
            CEP.TRC(SCCGWA, WS_TACCUM.WS_TACCUM_KEY.WS_TACCUM_EFFD);
            CEP.TRC(SCCGWA, WS_TACCUM.WS_TACCUM_KEY.WS_TACCUM_BOOK);
            CEP.TRC(SCCGWA, WS_TACCUM.WS_TACCUM_KEY.WS_TACCUM_CCY);
        }
        CEP.TRC(SCCGWA, "*****END*****");
        IBS.init(SCCGWA, WS_TACCUM);
        WS_TACCUM.WS_TACCUM_AMT = 0;
        WS_ACCUMT_CNT = WS_ACCUM_CNT;
        WS_ITM_RM_FLG = 'R';
        CEP.TRC(SCCGWA, WS_ACCUMT_CNT);
        for (WS_COUNTER.WS_IDX_J = 1; WS_COUNTER.WS_IDX_J <= WS_ACCUMT_CNT; WS_COUNTER.WS_IDX_J += 1) {
            CEP.TRC(SCCGWA, WS_COUNTER.WS_IDX_J);
            if (WS_ACCUM[WS_COUNTER.WS_IDX_J-1].WS_ACCUM_KEY.WS_ACCUM_EFFD == WS_TACCUM.WS_TACCUM_KEY.WS_TACCUM_EFFD 
                && WS_ACCUM[WS_COUNTER.WS_IDX_J-1].WS_ACCUM_KEY.WS_ACCUM_BOOK.equalsIgnoreCase(WS_TACCUM.WS_TACCUM_KEY.WS_TACCUM_BOOK) 
                && WS_ACCUM[WS_COUNTER.WS_IDX_J-1].WS_ACCUM_KEY.WS_ACCUM_CCY.equalsIgnoreCase(WS_TACCUM.WS_TACCUM_KEY.WS_TACCUM_CCY)) {
                CEP.TRC(SCCGWA, WS_TACCUM.WS_TACCUM_AMT);
                CEP.TRC(SCCGWA, WS_ACCUM[WS_COUNTER.WS_IDX_J-1].WS_ACCUM_AMT);
                WS_TACCUM.WS_TACCUM_AMT = WS_TACCUM.WS_TACCUM_AMT + WS_ACCUM[WS_COUNTER.WS_IDX_J-1].WS_ACCUM_AMT;
            } else {
                B950_NOT_BAL_PROC();
                if (pgmRtn) return;
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_ACCUM[WS_COUNTER.WS_IDX_J-1]);
                IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_TACCUM);
            }
        }
        B950_NOT_BAL_PROC();
        if (pgmRtn) return;
        WS_ITM_RM_FLG = ' ';
    }
    public void B950_NOT_BAL_PROC() throws IOException,SQLException,Exception {
        if (WS_TACCUM.WS_TACCUM_AMT != 0) {
            WS_BUSINESS_MSG_LVL = 'W';
            WS_VAL_CHK_ERR_FLG = 'Y';
            WS_COMM_ERR_MSG = AICMSG_ERROR_MSG.AI_VCH_NOT_BAL;
            WS_BOOK_FLG = WS_TACCUM.WS_TACCUM_KEY.WS_TACCUM_BOOK;
            B911_CHK_BOOK_FLG();
            if (pgmRtn) return;
        }
    }
    public void B960_INTER_BRANCH() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_ACCUM_CNT);
        for (WS_COUNTER.WS_IDX_K = 1; WS_COUNTER.WS_IDX_K <= WS_ACCUM_CNT; WS_COUNTER.WS_IDX_K += 1) {
            if (WS_ACCUM[WS_COUNTER.WS_IDX_K-1].WS_ACCUM_AMT != 0 
                && WS_ACCUM[WS_COUNTER.WS_IDX_K-1].WS_ACCUM_KEY.WS_ACCUM_EFFD <= 0) {
                WS_BOOK_FLG = WS_ACCUM[WS_COUNTER.WS_IDX_K-1].WS_ACCUM_KEY.WS_ACCUM_BOOK;
                B911_CHK_BOOK_FLG();
                if (pgmRtn) return;
                R000_GET_CRS_IA_R();
                if (pgmRtn) return;
                R000_INR_BR_SUS();
                if (pgmRtn) return;
            }
        }
    }
    public void B980_CRS_VIL_PROCESS() throws IOException,SQLException,Exception {
        for (WS_INT_NUM = 1; WS_INT_NUM <= 400; WS_INT_NUM += 1) {
            WS_VIL_ACUM[WS_INT_NUM-1].WS_VIL_DATA.WS_VIL_BR = 0;
            WS_VIL_ACUM[WS_INT_NUM-1].WS_VIL_DATA.WS_VIL_TYP = " ";
            WS_VIL_ACUM[WS_INT_NUM-1].WS_VIL_DATA.WS_VIL_CCY = " ";
            WS_VIL_ACUM[WS_INT_NUM-1].WS_VIL_DATA.WS_VIL_SIGN = ' ';
            WS_VIL_ACUM[WS_INT_NUM-1].WS_VIL_DATA.WS_VIL_AMT = 0;
        }
        for (WS_COUNTER.WS_IDX_K = 1; WS_COUNTER.WS_IDX_K <= WS_ACCUM_CNT; WS_COUNTER.WS_IDX_K += 1) {
            WS_VIL_FOUND_FLG = 'N';
            if (WS_ACCUM[WS_COUNTER.WS_IDX_K-1].WS_ACCUM_AMT != 0 
                && WS_ACCUM[WS_COUNTER.WS_IDX_K-1].WS_ACCUM_KEY.WS_ACCUM_EFFD <= 0) {
                R000_JUDGE_VIL_BRANCH();
                if (pgmRtn) return;
                R000_ADD_VIL_CLS();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, WS_VIL_CNT);
        R000_SORT_VIL_PROC();
        if (pgmRtn) return;
        for (WS_COUNTER.WS_IDX_K = 1; WS_COUNTER.WS_IDX_K <= WS_VIL_CNT; WS_COUNTER.WS_IDX_K += 1) {
            if (WS_VIL_ACUM[WS_COUNTER.WS_IDX_K-1].WS_VIL_DATA.WS_VIL_AMT != 0) {
                R000_TRANS_IB_CASH();
                if (pgmRtn) return;
                S000_CALL_IBZCASH();
                if (pgmRtn) return;
            }
        }
    }
    public void R000_SORT_VIL_PROC() throws IOException,SQLException,Exception {
        for (WS_I1 = 1; WS_I1 <= WS_VIL_CNT; WS_I1 += 1) {
            WS_I2 = WS_VIL_CNT - WS_I1;
            for (WS_I3 = 1; WS_I3 <= WS_I2; WS_I3 += 1) {
                WS_I4 = WS_I3 + 1;
                WS_COMPA_KEY.WS_COMPA_BR = WS_VIL_ACUM[WS_I3-1].WS_VIL_DATA.WS_VIL_BR;
                WS_COMPA_KEY.WS_COMPA_TYP = WS_VIL_ACUM[WS_I3-1].WS_VIL_DATA.WS_VIL_TYP;
                WS_COMPA_KEY.WS_COMPA_TRA = WS_VIL_ACUM[WS_I3-1].WS_VIL_DATA.WS_TRA_TYP;
                WS_COMPA_KEY.WS_COMPA_CCY = WS_VIL_ACUM[WS_I3-1].WS_VIL_DATA.WS_VIL_CCY;
                WS_COMPA_KEY.WS_COMPA_SIGN = WS_VIL_ACUM[WS_I3-1].WS_VIL_DATA.WS_VIL_SIGN;
                WS_COMPB_KEY.WS_COMPB_BR = WS_VIL_ACUM[WS_I4-1].WS_VIL_DATA.WS_VIL_BR;
                WS_COMPB_KEY.WS_COMPB_TYP = WS_VIL_ACUM[WS_I4-1].WS_VIL_DATA.WS_VIL_TYP;
                WS_COMPB_KEY.WS_COMPB_TRA = WS_VIL_ACUM[WS_I4-1].WS_VIL_DATA.WS_TRA_TYP;
                WS_COMPB_KEY.WS_COMPB_CCY = WS_VIL_ACUM[WS_I4-1].WS_VIL_DATA.WS_VIL_CCY;
                WS_COMPB_KEY.WS_COMPB_SIGN = WS_VIL_ACUM[WS_I4-1].WS_VIL_DATA.WS_VIL_SIGN;
                JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, WS_COMPA_KEY);
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_COMPB_KEY);
                if (JIBS_tmp_str[1].compareTo(JIBS_tmp_str[0]) > 0) {
                    JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_VIL_ACUM[WS_I3-1].WS_VIL_DATA);
                    IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_VIL_TMP_DATA);
                    JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_VIL_ACUM[WS_I4-1].WS_VIL_DATA);
                    IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_VIL_ACUM[WS_I3-1].WS_VIL_DATA);
                    JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_VIL_TMP_DATA);
                    IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_VIL_ACUM[WS_I4-1].WS_VIL_DATA);
                }
            }
        }
    }
    public void R000_TRANS_IB_CASH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCCASH);
        IBCCASH.FUNC = 'M';
        IBCCASH.POST_CTR = WS_VIL_ACUM[WS_COUNTER.WS_IDX_K-1].WS_VIL_DATA.WS_VIL_BR;
        IBCCASH.CORP_ID = WS_VIL_ACUM[WS_COUNTER.WS_IDX_K-1].WS_VIL_DATA.WS_VIL_TYP;
        IBCCASH.CCY = WS_VIL_ACUM[WS_COUNTER.WS_IDX_K-1].WS_VIL_DATA.WS_VIL_CCY;
        if (SCCGWA.COMM_AREA.VCH_NO.trim().length() == 0) IBCCASH.VCH_NO = 0;
        else IBCCASH.VCH_NO = Long.parseLong(SCCGWA.COMM_AREA.VCH_NO);
        IBCCASH.VCH_SEQ = (short) WS_COUNTER.WS_IDX_K;
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = IBCCASH.POST_CTR;
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPQORG.VIL_TYP);
        CEP.TRC(SCCGWA, BPCPQORG.TRA_TYP);
        if (BPCPQORG.TRA_TYP.equalsIgnoreCase(K_NON_TRA_FLG) 
            && BPCPQORG.VIL_TYP.equalsIgnoreCase(K_NON_VIL_FLG)) {
            if (WS_VIL_ACUM[WS_COUNTER.WS_IDX_K-1].WS_VIL_DATA.WS_VIL_AMT < 0) {
                IBCCASH.SIGN = 'C';
                WS_VIL_ACUM[WS_COUNTER.WS_IDX_K-1].WS_VIL_DATA.WS_VIL_AMT = WS_VIL_ACUM[WS_COUNTER.WS_IDX_K-1].WS_VIL_DATA.WS_VIL_AMT * -1;
            } else {
                IBCCASH.SIGN = 'D';
            }
        } else {
            if (WS_VIL_ACUM[WS_COUNTER.WS_IDX_K-1].WS_VIL_DATA.WS_VIL_AMT < 0) {
                IBCCASH.SIGN = 'D';
                WS_VIL_ACUM[WS_COUNTER.WS_IDX_K-1].WS_VIL_DATA.WS_VIL_AMT = WS_VIL_ACUM[WS_COUNTER.WS_IDX_K-1].WS_VIL_DATA.WS_VIL_AMT * -1;
            } else {
                IBCCASH.SIGN = 'C';
            }
        }
        IBCCASH.AMT = WS_VIL_ACUM[WS_COUNTER.WS_IDX_K-1].WS_VIL_DATA.WS_VIL_AMT;
        CEP.TRC(SCCGWA, IBCCASH.FUNC);
        CEP.TRC(SCCGWA, IBCCASH.POST_CTR);
        CEP.TRC(SCCGWA, IBCCASH.CORP_ID);
        CEP.TRC(SCCGWA, IBCCASH.CCY);
        CEP.TRC(SCCGWA, IBCCASH.SIGN);
        CEP.TRC(SCCGWA, IBCCASH.AMT);
    }
    public void R000_JUDGE_VIL_BRANCH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = WS_ACCUM[WS_COUNTER.WS_IDX_K-1].WS_ACCUM_KEY.WS_ACCUM_BR;
        if (BPCPQORG.BR != WS_LAST_BR) {
            S000_CALL_BPZPQORG();
            if (pgmRtn) return;
            if (WS_BR_FND_FLG == 'Y') {
                WS_LAST_BR = BPCPQORG.BR;
                WS_LAST_VIL = BPCPQORG.VIL_TYP;
                WS_LAST_TRA = BPCPQORG.TRA_TYP;
                WS_VTR_BR_FLG = BPCPQORG.INT_BR_FLG;
            }
        }
        CEP.TRC(SCCGWA, BPCPQORG.INT_BR_FLG);
        CEP.TRC(SCCGWA, BPCPQORG.TRA_TYP);
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        if (BPCPQORG.BR != WS_LAST_TR_BR) {
            S000_CALL_BPZPQORG();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCPQORG.BR);
            if (WS_BR_FND_FLG == 'Y') {
                WS_LAST_TR_BR = BPCPQORG.BR;
                WS_TX_VIL = BPCPQORG.VIL_TYP;
                WS_TX_TRA = BPCPQORG.TRA_TYP;
                CEP.TRC(SCCGWA, WS_TX_VIL);
            }
        }
        CEP.TRC(SCCGWA, WS_LAST_TRA);
        CEP.TRC(SCCGWA, BPCPQORG.INT_BR_FLG);
        if ((!WS_LAST_TRA.equalsIgnoreCase(K_NON_TRA_FLG) 
            && WS_VTR_BR_FLG == 'Y') 
            || !WS_LAST_VIL.equalsIgnoreCase(K_NON_VIL_FLG)) {
            WS_VIL_FLG = 'Y';
        } else {
            WS_VIL_FLG = 'N';
        }
    }
    public void R000_ADD_VIL_CLS() throws IOException,SQLException,Exception {
        if (WS_BR_FND_FLG == 'Y' 
            && WS_VIL_FLG == 'Y') {
            R000_ADD_VIL_INFO();
            if (pgmRtn) return;
        }
    }
    public void R000_SUM_VIL_INFO() throws IOException,SQLException,Exception {
        WS_VIL_FOUND_FLG = 'Y';
        if (WS_ACCUM[WS_COUNTER.WS_IDX_K-1].WS_ACCUM_KEY.WS_ACCUM_SIGN == 'D') {
            WS_VIL_ACUM[WS_COUNTER.WS_IDX_V-1].WS_VIL_DATA.WS_VIL_AMT = WS_VIL_ACUM[WS_COUNTER.WS_IDX_V-1].WS_VIL_DATA.WS_VIL_AMT + WS_ACCUM[WS_COUNTER.WS_IDX_K-1].WS_ACCUM_AMT;
        } else {
            WS_VIL_ACUM[WS_COUNTER.WS_IDX_V-1].WS_VIL_DATA.WS_VIL_AMT = WS_VIL_ACUM[WS_COUNTER.WS_IDX_V-1].WS_VIL_DATA.WS_VIL_AMT - WS_ACCUM[WS_COUNTER.WS_IDX_K-1].WS_ACCUM_AMT;
        }
    }
    public void R000_ADD_VIL_INFO() throws IOException,SQLException,Exception {
        WS_COR_FOUND_FLG = 'N';
        for (WS_I0 = 1; WS_I0 <= WS_VIL_CNT 
            && WS_COR_FOUND_FLG != 'Y'; WS_I0 += 1) {
            if (WS_ACCUM[WS_COUNTER.WS_IDX_K-1].WS_ACCUM_KEY.WS_ACCUM_CCY.equalsIgnoreCase(WS_VIL_ACUM[WS_I0-1].WS_VIL_DATA.WS_VIL_CCY) 
                && WS_LAST_TRA.equalsIgnoreCase(WS_VIL_ACUM[WS_I0-1].WS_VIL_DATA.WS_TRA_TYP) 
                && WS_LAST_VIL.equalsIgnoreCase(WS_VIL_ACUM[WS_I0-1].WS_VIL_DATA.WS_VIL_TYP)) {
                WS_COR_FOUND_FLG = 'Y';
            }
        }
        if (WS_COR_FOUND_FLG == 'N') {
            WS_VIL_CNT += 1;
        } else {
            WS_I0 = WS_I0 - 1;
        }
        WS_VIL_FOUND_FLG = 'Y';
        WS_VIL_ACUM[WS_I0-1].WS_VIL_DATA.WS_VIL_BR = WS_ACCUM[WS_COUNTER.WS_IDX_K-1].WS_ACCUM_KEY.WS_ACCUM_BR;
        WS_VIL_ACUM[WS_I0-1].WS_VIL_DATA.WS_VIL_CCY = WS_ACCUM[WS_COUNTER.WS_IDX_K-1].WS_ACCUM_KEY.WS_ACCUM_CCY;
        WS_VIL_ACUM[WS_I0-1].WS_VIL_DATA.WS_TRA_TYP = WS_LAST_TRA;
        WS_VIL_ACUM[WS_I0-1].WS_VIL_DATA.WS_VIL_TYP = WS_LAST_VIL;
        WS_VIL_ACUM[WS_I0-1].WS_VIL_DATA.WS_VIL_AMT = WS_VIL_ACUM[WS_I0-1].WS_VIL_DATA.WS_VIL_AMT + WS_ACCUM[WS_COUNTER.WS_IDX_K-1].WS_ACCUM_AMT;
        CEP.TRC(SCCGWA, WS_VIL_CNT);
        CEP.TRC(SCCGWA, WS_VIL_ACUM[WS_VIL_CNT-1].WS_VIL_DATA.WS_VIL_BR);
        CEP.TRC(SCCGWA, WS_VIL_ACUM[WS_VIL_CNT-1].WS_VIL_DATA.WS_VIL_CCY);
        CEP.TRC(SCCGWA, WS_VIL_ACUM[WS_VIL_CNT-1].WS_VIL_DATA.WS_VIL_TYP);
        CEP.TRC(SCCGWA, WS_VIL_ACUM[WS_VIL_CNT-1].WS_VIL_DATA.WS_VIL_AMT);
    }
    public void R000_GET_CRS_IA_R() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_CRS_SUS_INFO.WS_CRS_SUS_R);
        CEP.TRC(SCCGWA, WS_CRS_SUS_INFO.WS_CRS_SEQ_R);
        if (WS_CRS_SUS_INFO.WS_CRS_SUS_R.trim().length() > 0 
            && WS_CRS_SUS_INFO.WS_CRS_SEQ_R != 0) {
            WS_INTERNAL_AC.WS_INTERNAL_BR = WS_ACCUM[WS_COUNTER.WS_IDX_K-1].WS_ACCUM_KEY.WS_ACCUM_BR;
            WS_INTERNAL_AC.WS_INTERNAL_ITM = WS_CRS_SUS_INFO.WS_CRS_SUS_R;
            WS_INTERNAL_AC.WS_INTERNAL_SEQ = WS_CRS_SUS_INFO.WS_CRS_SEQ_R;
            WS_INTERNAL_AC.WS_INTERNAL_CCY = WS_ACCUM[WS_COUNTER.WS_IDX_K-1].WS_ACCUM_KEY.WS_ACCUM_CCY;
            WS_CRS_SUS_INFO.WS_CRS_IA_R = IBS.CLS2CPY(SCCGWA, WS_INTERNAL_AC);
        } else {
            if (WS_CRS_SUS_INFO.WS_CRS_SUS_R.trim().length() == 0) {
                WS_ERR_MSG = AICMSG_ERROR_MSG.AI_ITM_MST_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void R000_ADD_BAL_ACCUM() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "*******BAL-ACCUM******");
        CEP.TRC(SCCGWA, AIRAIVX.VCHT_DATA.PARTB.EFF_DAYS);
        CEP.TRC(SCCGWA, AIRAIVX.VCHT_DATA.PARTB.BOOK_FLG);
        CEP.TRC(SCCGWA, AIRAIVX.VCHT_DATA.PARTB.CCY);
        CEP.TRC(SCCGWA, AIRAIVX.VCHT_DATA.PARTB.VAL_DATE);
        CEP.TRC(SCCGWA, AIRAIVX.VCHT_DATA.PARTB.BR);
        CEP.TRC(SCCGWA, AIRAIVX.VCHT_DATA.PARTB.SIGN);
        CEP.TRC(SCCGWA, AIRAIVX.VCHT_DATA.PARTB.AMT);
        CEP.TRC(SCCGWA, "*******BAL-ACCUM******");
        CEP.TRC(SCCGWA, AIRAIVX.ITEM_DATA.ITM_POST_TYPE);
        if (AIRAIVX.ITEM_DATA.ITM_POST_TYPE == 'R') {
            B920_ACCUM_VCH();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_AITITUS() throws IOException,SQLException,Exception {
        AITITUS_RD = new DBParm();
        AITITUS_RD.TableName = "AITITUS";
        IBS.READ(SCCGWA, AIRITUS, AITITUS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FND_AUTH_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FND_AUTH_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "AITITUS";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_AITITUS() throws IOException,SQLException,Exception {
        AITITUS_BR.rp = new DBParm();
        AITITUS_BR.rp.TableName = "AITITUS";
        AITITUS_BR.rp.where = "ITM_NO = :AIRITUS.KEY.ITM_NO";
        IBS.STARTBR(SCCGWA, AIRITUS, this, AITITUS_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FND_AUTH_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FND_AUTH_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "AITITUS";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_AITITUS_1() throws IOException,SQLException,Exception {
        AITITUS_BR.rp = new DBParm();
        AITITUS_BR.rp.TableName = "AITITUS";
        AITITUS_BR.rp.grp = "ITM_NO";
        IBS.STARTBR(SCCGWA, AIRITUS, AITITUS_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FND_AUTH_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FND_AUTH_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "AITITUS";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_AITITUS() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, AITITUS_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FND_AUTH_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FND_AUTH_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "AITITUS";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READNEXT_AITITUS() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, AIRITUS, this, AITITUS_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FND_AUTH_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FND_AUTH_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "AITITUS";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_BR_FND_FLG = 'N';
        } else {
            WS_BR_FND_FLG = 'Y';
        }
    }
    public void S000_CALL_IBZCASH() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-CORP-CASH-PROC", IBCCASH);
        if (IBCCASH.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, IBCCASH.RC);
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (AICOCKVH.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "AICOCKVH = ");
            CEP.TRC(SCCGWA, AICOCKVH);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
