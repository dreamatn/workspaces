package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSTLIB {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZSTLIB";
    String CPN_R_TLR_MAINTAIN = "BP-R-TLR-MAINTAIN   ";
    String CPN_R_STARTBR_TLT = "BP-R-STARTBR-TLT    ";
    BPZSTLIB_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new BPZSTLIB_WS_TEMP_VARIABLE();
    String WS_ERR_MSG = " ";
    char WS_TBL_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCRTLTB BPCRTLTB = new BPCRTLTB();
    BPCOTLIB BPCOTLIB = new BPCOTLIB();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPRTLT BPRTLT = new BPRTLT();
    SCCGWA SCCGWA;
    BPCSTLIB BPCSTLIB;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCSTLIB BPCSTLIB) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSTLIB = BPCSTLIB;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSTLIB return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTLT);
        IBS.init(SCCGWA, BPCRTLTB);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if ((BPCSTLIB.TLR.trim().length() == 0) 
            && (BPCSTLIB.TLR_BR == ' ')) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BNK = SCCGWA.COMM_AREA.TR_BANK;
        BPCPQORG.BR = BPCSTLIB.TLR_BR;
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        WS_TBL_FLAG = 'Y';
        CEP.TRC(SCCGWA, BPCSTLIB.TLR);
        CEP.TRC(SCCGWA, BPCSTLIB.TLR_BR);
        CEP.TRC(SCCGWA, BPCSTLIB.TLR_CN_NM);
        CEP.TRC(SCCGWA, BPCSTLIB.TLR_LVL);
        CEP.TRC(SCCGWA, BPCSTLIB.TLR_STS);
        B010_STARTBR_PROCESS();
        if (pgmRtn) return;
        WS_TEMP_VARIABLE.WS_CNT = 0;
        while (WS_TBL_FLAG != 'N' 
            && SCCMPAG.FUNC != 'E') {
            B020_READNEXT_PROCESS();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, "11111");
            if (WS_TBL_FLAG == 'Y') {
                CEP.TRC(SCCGWA, "22222");
                WS_TEMP_VARIABLE.WS_CNT = WS_TEMP_VARIABLE.WS_CNT + 1;
                if (WS_TEMP_VARIABLE.WS_CNT == 1) {
                    B040_01_OUT_TITLE();
                    if (pgmRtn) return;
                }
                B040_02_OUT_BRW_DATA();
                if (pgmRtn) return;
            }
        }
        B030_ENDBR_PROCESS();
        if (pgmRtn) return;
        BPCSTLIB.CNT = WS_TEMP_VARIABLE.WS_CNT;
    }
    public void B010_STARTBR_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTLT);
        BPRTLT.TLR_BR = BPCSTLIB.TLR_BR;
        if (BPCSTLIB.TLR.trim().length() == 0) {
            BPRTLT.KEY.TLR = "%%%%%%%%";
        } else {
            BPRTLT.KEY.TLR = BPCSTLIB.TLR;
        }
        if (BPCSTLIB.TLR_CN_NM.trim().length() == 0) {
            CEP.TRC(SCCGWA, "XXXXXXXXX");
            BPRTLT.TLR_CN_NM = "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%";
        } else {
            CEP.TRC(SCCGWA, "YYYYYYYYY");
            BPRTLT.TLR_CN_NM = BPCSTLIB.TLR_CN_NM;
            BPRTLT.TLR_CN_NM = "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%";
        }
        if (BPCSTLIB.TLR_LVL == ' ') {
            BPRTLT.TLR_LVL = ALL.charAt(0);
        } else {
            BPRTLT.TLR_LVL = BPCSTLIB.TLR_LVL;
        }
        if (BPCSTLIB.TLR_STS == ' ') {
            BPRTLT.TLR_STS = ALL.charAt(0);
        } else {
            BPRTLT.TLR_STS = BPCSTLIB.TLR_STS;
        }
        BPCRTLTB.INFO.FUNC = 'Q';
        BPCRTLTB.INFO.LEN = 1404;
        BPCRTLTB.INFO.POINTER = BPRTLT;
        S000_CALL_BPZRTLTB();
        if (pgmRtn) return;
    }
    public void B020_READNEXT_PROCESS() throws IOException,SQLException,Exception {
        BPCRTLTB.INFO.FUNC = 'N';
        BPCRTLTB.INFO.POINTER = BPRTLT;
        BPCRTLTB.INFO.LEN = 1404;
        S000_CALL_BPZRTLTB();
        if (pgmRtn) return;
        if (BPCRTLTB.RETURN_INFO == 'F') {
            WS_TBL_FLAG = 'Y';
        } else if (BPCRTLTB.RETURN_INFO == 'N') {
            WS_TBL_FLAG = 'N';
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_DATE_RANGE_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B030_ENDBR_PROCESS() throws IOException,SQLException,Exception {
        BPCRTLTB.INFO.FUNC = 'E';
        BPCRTLTB.INFO.LEN = 1404;
        BPCRTLTB.INFO.POINTER = BPRTLT;
        S000_CALL_BPZRTLTB();
        if (pgmRtn) return;
        if (BPCRTLTB.RETURN_INFO != 'F') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_DATE_RANGE_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B040_01_OUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 270;
        SCCMPAG.SCR_ROW_CNT = 30;
        SCCMPAG.SCR_COL_CNT = 6;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B040_02_OUT_BRW_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOTLIB);
        BPCOTLIB.TLR = BPRTLT.KEY.TLR;
        BPCOTLIB.TLR_LVL = BPRTLT.TLR_LVL;
        BPCOTLIB.TLR_BR = BPRTLT.TLR_BR;
        BPCOTLIB.TLR_CN_NM = BPRTLT.TLR_CN_NM;
        BPCOTLIB.TLR_TYP = BPRTLT.TLR_TYP;
        BPCOTLIB.TLR_STS = BPRTLT.TLR_STS;
        CEP.TRC(SCCGWA, BPCOTLIB.TLR);
        CEP.TRC(SCCGWA, BPCOTLIB.TLR_LVL);
        CEP.TRC(SCCGWA, BPCOTLIB.TLR_BR);
        CEP.TRC(SCCGWA, BPCOTLIB.TLR_CN_NM);
        CEP.TRC(SCCGWA, BPCOTLIB.TLR_TYP);
        CEP.TRC(SCCGWA, BPCOTLIB.TLR_STS);
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, BPCOTLIB);
        SCCMPAG.DATA_LEN = 270;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZRTLTB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_STARTBR_TLT, BPCRTLTB);
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG        ", BPCPQORG);
        CEP.TRC(SCCGWA, BPCPQORG.RC);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
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
