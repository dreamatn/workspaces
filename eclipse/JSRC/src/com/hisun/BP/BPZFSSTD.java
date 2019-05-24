package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZFSSTD {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_U_MAINTAIN_FSTD = "BP-F-U-MAINTAIN-FSTD";
    String WS_ERR_MSG = " ";
    short WS_CNT = 0;
    BPZFSSTD_MPOUT_DATA MPOUT_DATA = new BPZFSSTD_MPOUT_DATA();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCOSTDO BPCOSTDO = new BPCOSTDO();
    SCCGWA SCCGWA;
    BPCOFSTD BPCOSSTD;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCOFSTD BPCOSSTD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCOSSTD = BPCOSSTD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZFSSTD return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCOSSTD.KEY);
        CEP.TRC(SCCGWA, BPCOSSTD.KEY.FREE_FMT);
        if (BPCOSSTD.FUNC == 'I') {
            B010_QUERY_PROCESS();
            if (pgmRtn) return;
        } else if (BPCOSSTD.FUNC == 'A') {
            B020_CREATE_PROCESS();
            if (pgmRtn) return;
        } else if (BPCOSSTD.FUNC == 'U') {
            B030_MODIFY_PROCESS();
            if (pgmRtn) return;
        } else if (BPCOSSTD.FUNC == 'D') {
            B040_DELETE_PROCESS();
            if (pgmRtn) return;
        } else if (BPCOSSTD.FUNC == 'B') {
            B050_BROWSE_PROCESS();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_FUNC_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B010_QUERY_PROCESS() throws IOException,SQLException,Exception {
        R000_CHECK_KEY_VALIDITY();
        if (pgmRtn) return;
        S000_CALL_BPZFUSTD();
        if (pgmRtn) return;
        R000_TRANS_DATA_OUTPUT();
        if (pgmRtn) return;
    }
    public void B020_CREATE_PROCESS() throws IOException,SQLException,Exception {
        S000_CALL_BPZFUSTD();
        if (pgmRtn) return;
        R000_TRANS_DATA_OUTPUT();
        if (pgmRtn) return;
    }
    public void B020_01_HISTORY_RECORD() throws IOException,SQLException,Exception {
    }
    public void B030_MODIFY_PROCESS() throws IOException,SQLException,Exception {
        S000_CALL_BPZFUSTD();
        if (pgmRtn) return;
        R000_TRANS_DATA_OUTPUT();
        if (pgmRtn) return;
    }
    public void B040_DELETE_PROCESS() throws IOException,SQLException,Exception {
        R000_CHECK_KEY_VALIDITY();
        if (pgmRtn) return;
        S000_CALL_BPZFUSTD();
        if (pgmRtn) return;
        R000_TRANS_DATA_OUTPUT();
        if (pgmRtn) return;
    }
    public void B050_BROWSE_PROCESS() throws IOException,SQLException,Exception {
        R000_BEGIN_MPAGE_OUTPUT();
        if (pgmRtn) return;
        B051_START_BROWSE_PROC();
        if (pgmRtn) return;
        B052_READ_NEXT_PROC();
        if (pgmRtn) return;
        while (BPCOSSTD.RETURN_INFOR != 'N' 
            && SCCMPAG.FUNC != 'E') {
            R000_TRANS_DATA_MPAGE_OUTPUT();
            if (pgmRtn) return;
            B052_READ_NEXT_PROC();
            if (pgmRtn) return;
        }
        B053_END_BROWSE_PROC();
        if (pgmRtn) return;
    }
    public void B051_START_BROWSE_PROC() throws IOException,SQLException,Exception {
        BPCOSSTD.OPT = 'S';
        S000_CALL_BPZFUSTD();
        if (pgmRtn) return;
    }
    public void B052_READ_NEXT_PROC() throws IOException,SQLException,Exception {
        BPCOSSTD.OPT = 'R';
        S000_CALL_BPZFUSTD();
        if (pgmRtn) return;
    }
    public void B053_END_BROWSE_PROC() throws IOException,SQLException,Exception {
        BPCOSSTD.OPT = 'E';
        S000_CALL_BPZFUSTD();
        if (pgmRtn) return;
    }
    public void R000_BEGIN_MPAGE_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 452;
        SCCMPAG.SCR_ROW_CNT = 0;
        SCCMPAG.SCR_COL_CNT = 0;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R000_TRANS_DATA_MPAGE_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, MPOUT_DATA);
        MPOUT_DATA.MPOUT_FEE_CD = BPCOSSTD.FEE_CD;
        MPOUT_DATA.MPOUT_FEE_DESC = BPCOSSTD.FEE_DESC;
        MPOUT_DATA.MPOUT_REG_CODE = BPCOSSTD.KEY.REG_CODE;
        MPOUT_DATA.MPOUT_CHN_NO = BPCOSSTD.KEY.CHN_NO;
        MPOUT_DATA.MPOUT_FREE_FMT = BPCOSSTD.KEY.FREE_FMT;
        MPOUT_DATA.MPOUT_REF_CCY = BPCOSSTD.KEY.REF_CCY;
        MPOUT_DATA.MPOUT_START_AMT = BPCOSSTD.VAL.START_AMT;
        MPOUT_DATA.MPOUT_FIX_AMT = BPCOSSTD.VAL.FIX_AMT;
        MPOUT_DATA.MPOUT_FEE_CCY = BPCOSSTD.VAL.FEE_CCY;
        MPOUT_DATA.MPOUT_MIN_AMT = BPCOSSTD.VAL.MIN_AMT;
        MPOUT_DATA.MPOUT_MAX_AMT = BPCOSSTD.VAL.MAX_AMT;
        MPOUT_DATA.MPOUT_CAL_TYPE = BPCOSSTD.VAL.CAL_TYPE;
        MPOUT_DATA.MPOUT_CAL_SOURCE = BPCOSSTD.VAL.CAL_SOURCE;
        MPOUT_DATA.MPOUT_CAL_CYC = BPCOSSTD.VAL.CAL_CYC;
        MPOUT_DATA.MPOUT_CYC_NUM = BPCOSSTD.VAL.CYC_NUM;
        MPOUT_DATA.MPOUT_AGR_TYPE = BPCOSSTD.VAL.AGR_TYPE;
        MPOUT_DATA.MPOUT_PRICE_MTH = BPCOSSTD.VAL.PRICE_MTH;
        MPOUT_DATA.MPOUT_AGGR_TYPE = BPCOSSTD.VAL.AGGR_TYPE;
        MPOUT_DATA.MPOUT_EFF_DATE = BPCOSSTD.DATE.EFF_DATE;
        MPOUT_DATA.MPOUT_EXP_DATE = BPCOSSTD.DATE.EXP_DATE;
        for (WS_CNT = 1; WS_CNT <= 5; WS_CNT += 1) {
            MPOUT_DATA.MPOUT_FEE_DATA[WS_CNT-1].MPOUT_UP_AMT = BPCOSSTD.VAL.FEE_DATA[WS_CNT-1].UP_AMT;
            MPOUT_DATA.MPOUT_FEE_DATA[WS_CNT-1].MPOUT_UP_CNT = BPCOSSTD.VAL.FEE_DATA[WS_CNT-1].UP_CNT;
            if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
                MPOUT_DATA.MPOUT_FEE_DATA[WS_CNT-1].MPOUT_AGG_MTH = BPCOSSTD.VAL.FEE_DATA[WS_CNT-1].AGG_MTH;
            }
            MPOUT_DATA.MPOUT_FEE_DATA[WS_CNT-1].MPOUT_FEE_AMT = BPCOSSTD.VAL.FEE_DATA[WS_CNT-1].FEE_AMT;
            MPOUT_DATA.MPOUT_FEE_DATA[WS_CNT-1].MPOUT_FEE_PER = BPCOSSTD.VAL.FEE_DATA[WS_CNT-1].FEE_PER;
        }
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, MPOUT_DATA);
        SCCMPAG.DATA_LEN = 452;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R000_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOSTDO);
        BPCOSTDO.FEE_CD = BPCOSSTD.FEE_CD;
        CEP.TRC(SCCGWA, BPCOSSTD.FEE_CD);
        CEP.TRC(SCCGWA, BPCOSTDO.FEE_CD);
        BPCOSTDO.FEE_DESC = BPCOSSTD.FEE_DESC;
        CEP.TRC(SCCGWA, BPCOSSTD.FEE_DESC);
        CEP.TRC(SCCGWA, BPCOSTDO.FEE_DESC);
        BPCOSTDO.REG_CODE = BPCOSSTD.KEY.REG_CODE;
        CEP.TRC(SCCGWA, BPCOSSTD.KEY.REG_CODE);
        CEP.TRC(SCCGWA, BPCOSTDO.REG_CODE);
        BPCOSTDO.CHN_NO = BPCOSSTD.KEY.CHN_NO;
        CEP.TRC(SCCGWA, BPCOSSTD.KEY.CHN_NO);
        CEP.TRC(SCCGWA, BPCOSTDO.CHN_NO);
        BPCOSTDO.FREE_FMT = BPCOSSTD.KEY.FREE_FMT;
        CEP.TRC(SCCGWA, BPCOSSTD.KEY.FREE_FMT);
        CEP.TRC(SCCGWA, BPCOSTDO.FREE_FMT);
        BPCOSTDO.REF_CCY = BPCOSSTD.KEY.REF_CCY;
        CEP.TRC(SCCGWA, BPCOSSTD.KEY.REF_CCY);
        CEP.TRC(SCCGWA, BPCOSTDO.REF_CCY);
        BPCOSTDO.START_AMT = BPCOSSTD.VAL.START_AMT;
        CEP.TRC(SCCGWA, BPCOSSTD.VAL.START_AMT);
        CEP.TRC(SCCGWA, BPCOSTDO.START_AMT);
        BPCOSTDO.FIX_AMT = BPCOSSTD.VAL.FIX_AMT;
        CEP.TRC(SCCGWA, BPCOSSTD.VAL.FIX_AMT);
        CEP.TRC(SCCGWA, BPCOSTDO.FIX_AMT);
        BPCOSTDO.FEE_CCY = BPCOSSTD.VAL.FEE_CCY;
        CEP.TRC(SCCGWA, BPCOSSTD.VAL.FEE_CCY);
        CEP.TRC(SCCGWA, BPCOSTDO.FEE_CCY);
        BPCOSTDO.MIN_AMT = BPCOSSTD.VAL.MIN_AMT;
        CEP.TRC(SCCGWA, BPCOSSTD.VAL.MIN_AMT);
        CEP.TRC(SCCGWA, BPCOSTDO.MIN_AMT);
        BPCOSTDO.MAX_AMT = BPCOSSTD.VAL.MAX_AMT;
        CEP.TRC(SCCGWA, BPCOSSTD.VAL.MAX_AMT);
        CEP.TRC(SCCGWA, BPCOSTDO.MAX_AMT);
        BPCOSTDO.CAL_TYPE = BPCOSSTD.VAL.CAL_TYPE;
        CEP.TRC(SCCGWA, BPCOSSTD.VAL.CAL_TYPE);
        CEP.TRC(SCCGWA, BPCOSTDO.CAL_TYPE);
        BPCOSTDO.CAL_SOURCE = BPCOSSTD.VAL.CAL_SOURCE;
        CEP.TRC(SCCGWA, BPCOSSTD.VAL.CAL_SOURCE);
        CEP.TRC(SCCGWA, BPCOSTDO.CAL_SOURCE);
        BPCOSTDO.CAL_CYC = BPCOSSTD.VAL.CAL_CYC;
        CEP.TRC(SCCGWA, BPCOSSTD.VAL.CAL_CYC);
        CEP.TRC(SCCGWA, BPCOSTDO.CAL_CYC);
        BPCOSTDO.CYC_NUM = BPCOSSTD.VAL.CYC_NUM;
        CEP.TRC(SCCGWA, BPCOSSTD.VAL.CYC_NUM);
        CEP.TRC(SCCGWA, BPCOSTDO.CYC_NUM);
        BPCOSTDO.AGR_TYPE = BPCOSSTD.VAL.AGR_TYPE;
        CEP.TRC(SCCGWA, BPCOSSTD.VAL.AGR_TYPE);
        CEP.TRC(SCCGWA, BPCOSTDO.AGR_TYPE);
        BPCOSTDO.PRICE_MTH = BPCOSSTD.VAL.PRICE_MTH;
        CEP.TRC(SCCGWA, BPCOSSTD.VAL.PRICE_MTH);
        CEP.TRC(SCCGWA, BPCOSTDO.PRICE_MTH);
        BPCOSTDO.AGGR_TYPE = BPCOSSTD.VAL.AGGR_TYPE;
        CEP.TRC(SCCGWA, BPCOSSTD.VAL.AGGR_TYPE);
        CEP.TRC(SCCGWA, BPCOSTDO.AGGR_TYPE);
        BPCOSTDO.TXT = BPCOSSTD.VAL.TXT;
        CEP.TRC(SCCGWA, BPCOSSTD.VAL.TXT);
        CEP.TRC(SCCGWA, BPCOSTDO.TXT);
        BPCOSTDO.EFF_DATE = BPCOSSTD.DATE.EFF_DATE;
        CEP.TRC(SCCGWA, BPCOSSTD.DATE.EFF_DATE);
        CEP.TRC(SCCGWA, BPCOSTDO.EFF_DATE);
        BPCOSTDO.EXP_DATE = BPCOSSTD.DATE.EXP_DATE;
        CEP.TRC(SCCGWA, BPCOSSTD.DATE.EXP_DATE);
        CEP.TRC(SCCGWA, BPCOSTDO.EXP_DATE);
        for (WS_CNT = 1; WS_CNT <= 10; WS_CNT += 1) {
            BPCOSTDO.FEE_DATA[WS_CNT-1].UP_AMT = BPCOSSTD.VAL.FEE_DATA[WS_CNT-1].UP_AMT;
            BPCOSTDO.FEE_DATA[WS_CNT-1].UP_CNT = BPCOSSTD.VAL.FEE_DATA[WS_CNT-1].UP_CNT;
            if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
                BPCOSTDO.FEE_DATA[WS_CNT-1].AGG_MTH = BPCOSSTD.VAL.FEE_DATA[WS_CNT-1].AGG_MTH;
            }
            BPCOSTDO.FEE_DATA[WS_CNT-1].FEE_AMT = BPCOSSTD.VAL.FEE_DATA[WS_CNT-1].FEE_AMT;
            BPCOSTDO.FEE_DATA[WS_CNT-1].FEE_PER = BPCOSSTD.VAL.FEE_DATA[WS_CNT-1].FEE_PER;
            CEP.TRC(SCCGWA, BPCOSSTD.VAL.FEE_DATA[WS_CNT-1].UP_AMT);
            CEP.TRC(SCCGWA, BPCOSSTD.VAL.FEE_DATA[WS_CNT-1].UP_CNT);
            CEP.TRC(SCCGWA, BPCOSSTD.VAL.FEE_DATA[WS_CNT-1].FEE_AMT);
            CEP.TRC(SCCGWA, BPCOSSTD.VAL.FEE_DATA[WS_CNT-1].FEE_PER);
            CEP.TRC(SCCGWA, BPCOSTDO.FEE_DATA[WS_CNT-1].UP_AMT);
            CEP.TRC(SCCGWA, BPCOSTDO.FEE_DATA[WS_CNT-1].UP_CNT);
            CEP.TRC(SCCGWA, BPCOSTDO.FEE_DATA[WS_CNT-1].AGG_MTH);
            CEP.TRC(SCCGWA, BPCOSTDO.FEE_DATA[WS_CNT-1].FEE_AMT);
            CEP.TRC(SCCGWA, BPCOSTDO.FEE_DATA[WS_CNT-1].FEE_PER);
        }
        CEP.TRC(SCCGWA, BPCOSTDO.START_AMT);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = BPCOSSTD.OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCOSTDO;
        SCCFMT.DATA_LEN = 756;
        IBS.FMT(SCCGWA, SCCFMT);
        CEP.TRC(SCCGWA, "VINCENT TEST ");
        CEP.TRC(SCCGWA, BPCOSTDO.CAL_TYPE);
    }
    public void R000_CHECK_KEY_VALIDITY() throws IOException,SQLException,Exception {
        if (BPCOSSTD.FEE_CD.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_CD_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCOSSTD.KEY.REF_CCY.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_REF_CCY_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void R000_CHECK_DATA_VALIDITY() throws IOException,SQLException,Exception {
        R000_CHECK_KEY_VALIDITY();
        if (pgmRtn) return;
        if (BPCOSSTD.VAL.FEE_CCY.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_CCY_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if ((BPCOSSTD.VAL.CAL_TYPE != '0') 
            && (BPCOSSTD.VAL.CAL_TYPE != '1')) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CAL_TYPE_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if ((BPCOSSTD.VAL.CAL_SOURCE != '0') 
            && (BPCOSSTD.VAL.CAL_SOURCE != '1')) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CAL_SOURCE_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if ((BPCOSSTD.VAL.CAL_CYC != '0') 
            && (BPCOSSTD.VAL.CAL_CYC != '1') 
            && (BPCOSSTD.VAL.CAL_CYC != '2') 
            && (BPCOSSTD.VAL.CAL_CYC != '3')) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CAL_CYC_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCOSSTD.VAL.CYC_NUM == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CYC_NUM_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if ((BPCOSSTD.VAL.AGR_TYPE != '0') 
            && (BPCOSSTD.VAL.AGR_TYPE != '1')) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_AGR_TYPE_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if ((BPCOSSTD.VAL.PRICE_MTH != '0') 
            && (BPCOSSTD.VAL.PRICE_MTH != '1')) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PRICE_MTH_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if ((BPCOSSTD.VAL.AGGR_TYPE != '0') 
            && (BPCOSSTD.VAL.AGGR_TYPE != '1')) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_AGGR_TYPE_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void R000_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
    }
    public void R000_TRANS_DATA_OUPUT() throws IOException,SQLException,Exception {
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_CALL_BPZFUSTD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_MAINTAIN_FSTD, BPCOSSTD);
    }
    public void B_MPAG() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        JIBS_tmp_str[9] = "SCZMPAG";
        Class<?>clazz = Class.forName(JIBS_tmp_str[9].trim());
        Object obj = clazz.newInstance();
        Method m = clazz.getDeclaredMethod("MP",new Class[]{SCCGWA.getClass(), SCCMPAG.getClass()});
        m.invoke(obj, SCCGWA, SCCMPAG);
        if (SCCGWA.COMM_AREA.EXCP_FLG == 'Y') {
            Z_RET();
            if (pgmRtn) return;
        }
    } else { //FROM #ELSE
    } //FROM #ENDIF
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
