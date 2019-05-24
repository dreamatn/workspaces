package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZFSAMO {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String PGM_SCSSCKDT = "SCSSCKDT";
    String CPN_U_MAINTAIN_FAMO = "BP-F-U-MAINTAIN-FAMO";
    String WS_ERR_MSG = " ";
    BPZFSAMO_WS_DATE WS_DATE = new BPZFSAMO_WS_DATE();
    BPZFSAMO_WS_APPORTION_DATA WS_APPORTION_DATA = new BPZFSAMO_WS_APPORTION_DATA();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCOAMOO BPCOAMOO = new BPCOAMOO();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCKDT SCCCKDT = new SCCCKDT();
    SCCGWA SCCGWA;
    BPCOFAMO BPCOSAMO;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCOFAMO BPCOSAMO) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCOSAMO = BPCOSAMO;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZFSAMO return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCOSAMO.FUNC == 'I') {
            B010_QUERY_PROCESS();
            if (pgmRtn) return;
        } else if (BPCOSAMO.FUNC == 'A') {
            B020_CREATE_PROCESS();
            if (pgmRtn) return;
            B020_01_HISTORY_RECORD();
            if (pgmRtn) return;
        } else if (BPCOSAMO.FUNC == 'U') {
            B030_MODIFY_PROCESS();
            if (pgmRtn) return;
            B020_01_HISTORY_RECORD();
            if (pgmRtn) return;
        } else if (BPCOSAMO.FUNC == 'B') {
            B040_BROWSE_PROCESS();
            if (pgmRtn) return;
        } else if (BPCOSAMO.FUNC == 'D') {
            B050_DELETE_PROCESS();
            if (pgmRtn) return;
            B020_01_HISTORY_RECORD();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCOSAMO.FUNC != 'B') {
            B060_TRANS_DATA_OUTPUT();
            if (pgmRtn) return;
        }
    }
    public void B010_QUERY_PROCESS() throws IOException,SQLException,Exception {
        S000_Q_OR_D_INPUT_CHECK();
        if (pgmRtn) return;
        S000_CALL_BPZFUAMO();
        if (pgmRtn) return;
    }
    public void B020_CREATE_PROCESS() throws IOException,SQLException,Exception {
        S000_ADD_OR_UPDATE_INPUT_CHECK();
        if (pgmRtn) return;
        R000_TRANS_DATA_PARAMETER();
        if (pgmRtn) return;
        S000_CALL_BPZFUAMO();
        if (pgmRtn) return;
    }
    public void B020_01_HISTORY_RECORD() throws IOException,SQLException,Exception {
    }
    public void B030_MODIFY_PROCESS() throws IOException,SQLException,Exception {
        S000_ADD_OR_UPDATE_INPUT_CHECK();
        if (pgmRtn) return;
        R000_TRANS_DATA_PARAMETER();
        if (pgmRtn) return;
        S000_CALL_BPZFUAMO();
        if (pgmRtn) return;
    }
    public void B040_BROWSE_PROCESS() throws IOException,SQLException,Exception {
        B041_READY_TS_HEAD_TIT();
        if (pgmRtn) return;
        B042_STARTBR_PROCESS();
        if (pgmRtn) return;
        B043_READNEXT_PROCESS();
        if (pgmRtn) return;
        while (BPCOSAMO.RETURN_INFOR != 'N' 
            && SCCMPAG.FUNC != 'E') {
            B061_TRANS_DATA_OUTPUT();
            if (pgmRtn) return;
            B043_READNEXT_PROCESS();
            if (pgmRtn) return;
        }
        B044_ENDBR_PROCESS();
        if (pgmRtn) return;
    }
    public void B041_READY_TS_HEAD_TIT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 132;
        SCCMPAG.SCR_ROW_CNT = 0;
        SCCMPAG.SCR_COL_CNT = 0;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B042_STARTBR_PROCESS() throws IOException,SQLException,Exception {
        BPCOSAMO.OPT = 'S';
        S000_CALL_BPZFUAMO();
        if (pgmRtn) return;
    }
    public void B043_READNEXT_PROCESS() throws IOException,SQLException,Exception {
        BPCOSAMO.OPT = 'N';
        S000_CALL_BPZFUAMO();
        if (pgmRtn) return;
    }
    public void B044_ENDBR_PROCESS() throws IOException,SQLException,Exception {
        BPCOSAMO.OPT = 'E';
        S000_CALL_BPZFUAMO();
        if (pgmRtn) return;
    }
    public void B050_DELETE_PROCESS() throws IOException,SQLException,Exception {
        S000_Q_OR_D_INPUT_CHECK();
        if (pgmRtn) return;
        R000_TRANS_DATA_PARAMETER();
        if (pgmRtn) return;
        S000_CALL_BPZFUAMO();
        if (pgmRtn) return;
    }
    public void B060_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOAMOO);
        BPCOAMOO.KEY.AMORT_CODE = BPCOSAMO.KEY.AMORT_CODE;
        BPCOAMOO.VAL.AMO_DESC = BPCOSAMO.VAL.AMO_DESC;
        BPCOAMOO.DATE.EFF_DATE = BPCOSAMO.DATE.EFF_DATE;
        BPCOAMOO.DATE.EXP_DATE = BPCOSAMO.DATE.EXP_DATE;
        BPCOAMOO.VAL.AMO_MTH = BPCOSAMO.VAL.AMO_MTH;
        BPCOAMOO.VAL.AMO_TMS = BPCOSAMO.VAL.AMO_TMS;
        BPCOAMOO.VAL.AMO_CYCLE = BPCOSAMO.VAL.AMO_CYCLE;
        BPCOAMOO.VAL.AMO_CNT = BPCOSAMO.VAL.AMO_CNT;
        BPCOAMOO.VAL.AMO_DATE = BPCOSAMO.VAL.AMO_DATE;
        BPCOAMOO.VAL.AMO_CCY = BPCOSAMO.VAL.AMO_CCY;
        BPCOAMOO.VAL.MIN_AMT = BPCOSAMO.VAL.MIN_AMT;
        BPCOAMOO.VAL.MAX_AMT = BPCOSAMO.VAL.MAX_AMT;
        BPCOAMOO.VAL.BACK_FLG = BPCOSAMO.VAL.BACK_FLG;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = BPCOSAMO.OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCOAMOO;
        SCCFMT.DATA_LEN = 134;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B061_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_APPORTION_DATA);
        WS_APPORTION_DATA.WS_AMORT_CODE = BPCOSAMO.KEY.AMORT_CODE;
        WS_APPORTION_DATA.WS_AMO_DESC = BPCOSAMO.VAL.AMO_DESC;
        WS_APPORTION_DATA.WS_EFF_DATE = BPCOSAMO.DATE.EFF_DATE;
        WS_APPORTION_DATA.WS_EXP_DATE = BPCOSAMO.DATE.EXP_DATE;
        WS_APPORTION_DATA.WS_AMO_MTH = BPCOSAMO.VAL.AMO_MTH;
        WS_APPORTION_DATA.WS_AMO_TMS = BPCOSAMO.VAL.AMO_TMS;
        WS_APPORTION_DATA.WS_AMO_CYCLE = BPCOSAMO.VAL.AMO_CYCLE;
        WS_APPORTION_DATA.WS_AMO_CNT = BPCOSAMO.VAL.AMO_CNT;
        WS_APPORTION_DATA.WS_AMO_DATE = BPCOSAMO.VAL.AMO_DATE;
        WS_APPORTION_DATA.WS_AMO_CCY = BPCOSAMO.VAL.AMO_CCY;
        WS_APPORTION_DATA.WS_MIN_AMT = BPCOSAMO.VAL.MIN_AMT;
        WS_APPORTION_DATA.WS_MAX_AMT = BPCOSAMO.VAL.MAX_AMT;
        WS_APPORTION_DATA.WS_BACK_FLG = BPCOSAMO.VAL.BACK_FLG;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_APPORTION_DATA);
        SCCMPAG.DATA_LEN = 132;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void S000_ADD_OR_UPDATE_INPUT_CHECK() throws IOException,SQLException,Exception {
        if (BPCOSAMO.KEY.AMORT_CODE.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FAMO_AMO_CODE_NOTINP;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCOSAMO.DATE.EFF_DATE < SCCGWA.COMM_AREA.AC_DATE) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_EFFDT_LT_ACDT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCOSAMO.DATE.EXP_DATE <= BPCOSAMO.DATE.EFF_DATE) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_EFFDT_GT_EXPDT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCOSAMO.VAL.AMO_MTH == ' ') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FAMO_AMO_MTH_NOTINPU;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCOSAMO.VAL.AMO_MTH != '0' 
            && BPCOSAMO.VAL.AMO_MTH != '1' 
            && BPCOSAMO.VAL.AMO_MTH != '2') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FAMO_AMO_MTH_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCOSAMO.VAL.AMO_TMS.equalsIgnoreCase("0") 
            && BPCOSAMO.VAL.AMO_MTH == '1') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FAMO_TERM_NOTINPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCOSAMO.VAL.AMO_CYCLE == ' ' 
            && BPCOSAMO.VAL.AMO_MTH == '1') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FAMO_AMO_CYC_NOTINPU;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCOSAMO.VAL.AMO_CYCLE != '0' 
            && BPCOSAMO.VAL.AMO_CYCLE != '1' 
            && BPCOSAMO.VAL.AMO_CYCLE != '2' 
            && BPCOSAMO.VAL.AMO_CYCLE != '3' 
            && BPCOSAMO.VAL.AMO_MTH == '1') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FAMO_CYC_INVALID;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCOSAMO.VAL.AMO_CNT.equalsIgnoreCase("0") 
            && BPCOSAMO.VAL.AMO_MTH == '1') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FAMO_AMO_CNT_NOTINPU;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCOSAMO.VAL.MIN_AMT == 0 
            && BPCOSAMO.VAL.AMO_MTH == '1') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FAMO_MIN_AMT_NOTINPU;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCOSAMO.VAL.MAX_AMT == 0 
            && BPCOSAMO.VAL.AMO_MTH == '1') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FAMO_MAX_AMT_NOTINPU;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCOSAMO.VAL.AMO_CYCLE == '2') {
            if ((BPCOSAMO.VAL.AMO_DATE < 1) 
                || (BPCOSAMO.VAL.AMO_DATE > 7)) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FAMO_AMO_DATE_ERR2;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (BPCOSAMO.VAL.AMO_CYCLE == '1') {
            if ((BPCOSAMO.VAL.AMO_DATE < 1) 
                || (BPCOSAMO.VAL.AMO_DATE > 31)) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FAMO_AMO_DATE_ERR1;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (BPCOSAMO.VAL.AMO_CYCLE == '0') {
            IBS.init(SCCGWA, SCCCKDT);
            IBS.CPY2CLS(SCCGWA, SCCGWA.COMM_AREA.TR_DATE+"", WS_DATE);
            IBS.CPY2CLS(SCCGWA, BPCOSAMO.VAL.AMO_DATE+"", WS_DATE.WS_MMDD);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_DATE);
            SCCCKDT.DATE = Integer.parseInt(JIBS_tmp_str[0]);
            SCSSCKDT SCSSCKDT1 = new SCSSCKDT();
            SCSSCKDT1.MP(SCCGWA, SCCCKDT);
            if (SCCCKDT.RC != 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FAMO_AMO_DATE_ERR0;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (BPCOSAMO.VAL.MAX_AMT < BPCOSAMO.VAL.MIN_AMT) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FAMO_AMT_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_Q_OR_D_INPUT_CHECK() throws IOException,SQLException,Exception {
        if (BPCOSAMO.KEY.AMORT_CODE.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FAMO_AMO_CODE_NOTINP;
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
    public void S000_CALL_BPZFUAMO() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_MAINTAIN_FAMO, BPCOSAMO);
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
