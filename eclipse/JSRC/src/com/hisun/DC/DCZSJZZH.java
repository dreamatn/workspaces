package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;
import com.hisun.CI.*;
import com.hisun.TD.*;
import com.hisun.DD.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZSJZZH {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    boolean pgmRtn = false;
    String OUTPUT_FMT = "DC312";
    int MAX_ROW = 10;
    int MAX_COL = 0;
    int COL_CNT = 0;
    DCZSJZZH_WS_VARIABLES WS_VARIABLES = new DCZSJZZH_WS_VARIABLES();
    DCZSJZZH_WS_OUTPUT_DATA WS_OUTPUT_DATA = new DCZSJZZH_WS_OUTPUT_DATA();
    DCCMSG_ERROR_MSG ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    CICQACRL CICQACRL = new CICQACRL();
    DCCUCINF DCCUCINF = new DCCUCINF();
    TDCACE TDCACE = new TDCACE();
    DDCSQAC DDCSQAC = new DDCSQAC();
    DDCSQAC_WS_SQL_VARIABLES WS_SQL_VARIABLES = new DDCSQAC_WS_SQL_VARIABLES();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SC_AREA;
    SCCGBPA_BP_AREA BP_AREA;
    DCCSJZZH DCCSJZZH;
    public void MP(SCCGWA SCCGWA, DCCSJZZH DCCSJZZH) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCSJZZH = DCCSJZZH;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZSJZZH return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, WS_VARIABLES);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_GET_ACO_AC_INF();
        if (pgmRtn) return;
    }
    public void B010_GET_ACO_AC_INF() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCSJZZH.CARD_NO);
        if (DCCSJZZH.CARD_NO.trim().length() == 0) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, CICQACRL);
        CICQACRL.DATA.AC_REL = "05";
        CICQACRL.DATA.REL_AC_NO = DCCSJZZH.CARD_NO;
        CICQACRL.FUNC = 'I';
        S000_CALL_CIZQACRL();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQACRL.RC);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICQACRL.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase("CI8054")) {
            WS_VARIABLES.WS_AC_FLG.CZZH_FLG = 'N';
        } else {
            WS_VARIABLES.WS_AC_FLG.CZZH_FLG = 'Y';
            WS_VARIABLES.CZZH_AC = CICQACRL.O_DATA.O_AC_NO;
            CEP.TRC(SCCGWA, WS_VARIABLES.CZZH_AC);
        }
        IBS.init(SCCGWA, CICQACRL);
        CICQACRL.DATA.AC_REL = "06";
        CICQACRL.DATA.REL_AC_NO = DCCSJZZH.CARD_NO;
        CICQACRL.FUNC = 'I';
        S000_CALL_CIZQACRL();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQACRL.RC);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICQACRL.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase("CI8054")) {
            WS_VARIABLES.WS_AC_FLG.XXT_FLG = 'N';
        } else {
            WS_VARIABLES.WS_AC_FLG.XXT_FLG = 'Y';
            WS_VARIABLES.XXT_AC = CICQACRL.O_DATA.O_AC_NO;
            CEP.TRC(SCCGWA, WS_VARIABLES.CZZH_AC);
        }
        B030_01_OUT_TITLE();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DD-PROC");
        IBS.init(SCCGWA, DDCSQAC);
        DDCSQAC.INPUT_INFO.FUNC = 'A';
        DDCSQAC.INPUT_INFO.AC_NO = DCCSJZZH.CARD_NO;
        S000_CALL_DDZSQAC();
        if (pgmRtn) return;
        WS_VARIABLES.I = 1;
        while (WS_VARIABLES.I <= 100 
            && DDCSQAC.OUTPUT_DATA.ACAC_INFO[WS_VARIABLES.I-1].ACO_NO.trim().length() != 0) {
            if (DDCSQAC.OUTPUT_DATA.ACAC_INFO[WS_VARIABLES.I-1].AC_TYPE == '2' 
                || DDCSQAC.OUTPUT_DATA.ACAC_INFO[WS_VARIABLES.I-1].AC_TYPE == '4' 
                || DDCSQAC.OUTPUT_DATA.ACAC_INFO[WS_VARIABLES.I-1].AC_TYPE == '5' 
                || DDCSQAC.OUTPUT_DATA.ACAC_INFO[WS_VARIABLES.I-1].AC_TYPE == '8' 
                || DDCSQAC.OUTPUT_DATA.ACAC_INFO[WS_VARIABLES.I-1].AC_TYPE == 'A' 
                || DDCSQAC.OUTPUT_DATA.ACAC_INFO[WS_VARIABLES.I-1].AC_TYPE == 'B' 
                || DDCSQAC.OUTPUT_DATA.ACAC_INFO[WS_VARIABLES.I-1].AC_TYPE == '1' 
                || DDCSQAC.OUTPUT_DATA.ACAC_INFO[WS_VARIABLES.I-1].AC_TYPE == 'C') {
                B015_GET_DD_AC_INF();
                if (pgmRtn) return;
                B030_DATA_OUTPUT_MPAG();
                if (pgmRtn) return;
            }
            WS_VARIABLES.I += 1;
        }
        CEP.TRC(SCCGWA, "DD-PROC-END");
        CEP.TRC(SCCGWA, "TD-PROC");
        IBS.init(SCCGWA, TDCACE);
        TDCACE.PAGE_INF.AC_NO = DCCSJZZH.CARD_NO;
        TDCACE.FMT_FLG = 'N';
        TDCACE.PAGE_INF.PAGE_NUM = 1;
        while (TDCACE.PAGE_INF.MY_FLG != 'Y') {
            S000_CALL_TDZACE();
            if (pgmRtn) return;
            WS_VARIABLES.I = 1;
            while (WS_VARIABLES.I <= 6 
                && TDCACE.DATA[WS_VARIABLES.I-1].ACO_AC.trim().length() != 0) {
                B016_GET_TD_AC_INF();
                if (pgmRtn) return;
                B030_DATA_OUTPUT_MPAG();
                if (pgmRtn) return;
                WS_VARIABLES.I += 1;
            }
            TDCACE.PAGE_INF.PAGE_NUM += 1;
            CEP.TRC(SCCGWA, TDCACE.PAGE_INF.MY_FLG);
        }
        if (WS_VARIABLES.WS_AC_FLG.CZZH_FLG == 'Y') {
            CEP.TRC(SCCGWA, "TD-CZZH");
            IBS.init(SCCGWA, TDCACE);
            TDCACE.PAGE_INF.AC_NO = WS_VARIABLES.CZZH_AC;
            TDCACE.PAGE_INF.I_AC_SEQ = 1;
            TDCACE.FMT_FLG = 'N';
            WS_VARIABLES.I = 1;
            S000_CALL_TDZACE();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, TDCACE.DATA[WS_VARIABLES.I-1].AC_SEQ);
            CEP.TRC(SCCGWA, TDCACE.DATA[WS_VARIABLES.I-1].ACO_AC);
            CEP.TRC(SCCGWA, TDCACE.DATA[WS_VARIABLES.I-1].BAL);
            B016_GET_TD_AC_INF();
            if (pgmRtn) return;
            WS_OUTPUT_DATA.TD_C_AC = WS_VARIABLES.CZZH_AC;
            WS_OUTPUT_DATA.TD_C_BAL = TDCACE.DATA[WS_VARIABLES.I-1].BAL;
            B030_DATA_OUTPUT_MPAG();
            if (pgmRtn) return;
        }
        if (WS_VARIABLES.WS_AC_FLG.XXT_FLG == 'Y') {
            CEP.TRC(SCCGWA, "TD-XXT");
            IBS.init(SCCGWA, TDCACE);
            TDCACE.PAGE_INF.AC_NO = WS_VARIABLES.XXT_AC;
            TDCACE.PAGE_INF.I_AC_SEQ = 1;
            TDCACE.FMT_FLG = 'N';
            WS_VARIABLES.I = 1;
            S000_CALL_TDZACE();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, TDCACE.DATA[WS_VARIABLES.I-1].AC_SEQ);
            CEP.TRC(SCCGWA, TDCACE.DATA[WS_VARIABLES.I-1].ACO_AC);
            CEP.TRC(SCCGWA, TDCACE.DATA[WS_VARIABLES.I-1].BAL);
            B016_GET_TD_AC_INF();
            if (pgmRtn) return;
            WS_OUTPUT_DATA.TD_X_AC = WS_VARIABLES.XXT_AC;
            WS_OUTPUT_DATA.TD_X_BAL = TDCACE.DATA[WS_VARIABLES.I-1].BAL;
            B030_DATA_OUTPUT_MPAG();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "TD-PROC-END");
    }
    public void B015_GET_DD_AC_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUTPUT_DATA);
        CEP.TRC(SCCGWA, DDCSQAC.OUTPUT_DATA.ACAC_INFO[WS_VARIABLES.I-1].ACO_NO);
        CEP.TRC(SCCGWA, DDCSQAC.OUTPUT_DATA.ACAC_INFO[WS_VARIABLES.I-1].AC_TYPE);
        CEP.TRC(SCCGWA, DDCSQAC.OUTPUT_DATA.ACAC_INFO[WS_VARIABLES.I-1].CCY);
        if (DDCSQAC.OUTPUT_DATA.ACAC_INFO[WS_VARIABLES.I-1].AC_TYPE == '1' 
            && !DDCSQAC.OUTPUT_DATA.ACAC_INFO[WS_VARIABLES.I-1].CCY.equalsIgnoreCase("156")) {
            WS_OUTPUT_DATA.AC_TYPE = '4';
        }
        if (DDCSQAC.OUTPUT_DATA.ACAC_INFO[WS_VARIABLES.I-1].AC_TYPE == '1' 
            && DDCSQAC.OUTPUT_DATA.ACAC_INFO[WS_VARIABLES.I-1].CCY.equalsIgnoreCase("156")) {
            WS_OUTPUT_DATA.AC_TYPE = '1';
        }
        if ((DDCSQAC.OUTPUT_DATA.ACAC_INFO[WS_VARIABLES.I-1].AC_TYPE == '2' 
            || DDCSQAC.OUTPUT_DATA.ACAC_INFO[WS_VARIABLES.I-1].AC_TYPE == '8') 
            && DDCSQAC.OUTPUT_DATA.ACAC_INFO[WS_VARIABLES.I-1].CCY.equalsIgnoreCase("156")) {
            WS_OUTPUT_DATA.AC_TYPE = '1';
        }
        if ((DDCSQAC.OUTPUT_DATA.ACAC_INFO[WS_VARIABLES.I-1].AC_TYPE == '2' 
            || DDCSQAC.OUTPUT_DATA.ACAC_INFO[WS_VARIABLES.I-1].AC_TYPE == '8') 
            && !DDCSQAC.OUTPUT_DATA.ACAC_INFO[WS_VARIABLES.I-1].CCY.equalsIgnoreCase("156")) {
            WS_OUTPUT_DATA.AC_TYPE = '4';
        }
        if (DDCSQAC.OUTPUT_DATA.ACAC_INFO[WS_VARIABLES.I-1].AC_TYPE == '4' 
            || DDCSQAC.OUTPUT_DATA.ACAC_INFO[WS_VARIABLES.I-1].AC_TYPE == 'A' 
            || DDCSQAC.OUTPUT_DATA.ACAC_INFO[WS_VARIABLES.I-1].AC_TYPE == 'C') {
            WS_OUTPUT_DATA.AC_TYPE = '2';
        }
        if (DDCSQAC.OUTPUT_DATA.ACAC_INFO[WS_VARIABLES.I-1].AC_TYPE == '5' 
            || DDCSQAC.OUTPUT_DATA.ACAC_INFO[WS_VARIABLES.I-1].AC_TYPE == 'B') {
            WS_OUTPUT_DATA.AC_TYPE = '3';
        }
        WS_OUTPUT_DATA.AC_PROD_CD = DDCSQAC.OUTPUT_DATA.ACAC_INFO[WS_VARIABLES.I-1].PROD_CD;
        WS_OUTPUT_DATA.CCY = DDCSQAC.OUTPUT_DATA.ACAC_INFO[WS_VARIABLES.I-1].CCY;
        WS_OUTPUT_DATA.CCY_TYP = DDCSQAC.OUTPUT_DATA.ACAC_INFO[WS_VARIABLES.I-1].CCY_TYPE;
        WS_OUTPUT_DATA.ISSU_DT = DDCSQAC.OUTPUT_DATA.ACAC_INFO[WS_VARIABLES.I-1].A_OPEN_DT;
        WS_OUTPUT_DATA.ISSU_BR = DDCSQAC.OUTPUT_DATA.ACAC_INFO[WS_VARIABLES.I-1].A_OPEN_BR;
        WS_OUTPUT_DATA.ISSU_TLR = DDCSQAC.OUTPUT_DATA.ACAC_INFO[WS_VARIABLES.I-1].A_OPEN_TLR;
        WS_OUTPUT_DATA.SDT = DDCSQAC.OUTPUT_DATA.ACAC_INFO[WS_VARIABLES.I-1].INT_DT;
        WS_OUTPUT_DATA.INT_RATE = DDCSQAC.OUTPUT_DATA.ACAC_INFO[WS_VARIABLES.I-1].RATE;
        WS_OUTPUT_DATA.DAC_FLG = DDCSQAC.OUTPUT_DATA.ACAC_INFO[WS_VARIABLES.I-1].DAC_FLG;
        WS_OUTPUT_DATA.OFFICE_FRZ_FLG = DDCSQAC.OUTPUT_DATA.ACAC_INFO[WS_VARIABLES.I-1].OFFICE_FRZ_FLG;
        WS_OUTPUT_DATA.BANK_FRZ_FLG = DDCSQAC.OUTPUT_DATA.ACAC_INFO[WS_VARIABLES.I-1].BANK_FRZ_FLG;
        WS_OUTPUT_DATA.OFFICE_FBD_FLG = DDCSQAC.OUTPUT_DATA.ACAC_INFO[WS_VARIABLES.I-1].OFFICE_FBD_FLG;
        WS_OUTPUT_DATA.BANK_FBD_FLG = DDCSQAC.OUTPUT_DATA.ACAC_INFO[WS_VARIABLES.I-1].BANK_FBD_FLG;
        WS_OUTPUT_DATA.ACO_AC = DDCSQAC.OUTPUT_DATA.ACAC_INFO[WS_VARIABLES.I-1].ACO_NO;
        WS_OUTPUT_DATA.OPEN_BAL = DDCSQAC.OUTPUT_DATA.ACAC_INFO[WS_VARIABLES.I-1].OPEN_BAL;
        WS_OUTPUT_DATA.CURR_BAL = DDCSQAC.OUTPUT_DATA.ACAC_INFO[WS_VARIABLES.I-1].CURR_BAL;
        WS_OUTPUT_DATA.AVL_BAL = DDCSQAC.OUTPUT_DATA.ACAC_INFO[WS_VARIABLES.I-1].AVL_BAL;
        WS_OUTPUT_DATA.HOLD_BAL = DDCSQAC.OUTPUT_DATA.ACAC_INFO[WS_VARIABLES.I-1].FRZ_BAL;
    }
    public void B016_GET_TD_AC_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUTPUT_DATA);
        WS_OUTPUT_DATA.ACO_AC = TDCACE.DATA[WS_VARIABLES.I-1].ACO_AC;
        WS_OUTPUT_DATA.AC_TYPE = '5';
        WS_OUTPUT_DATA.AC_SEQ = TDCACE.DATA[WS_VARIABLES.I-1].AC_SEQ;
        WS_OUTPUT_DATA.AC_PROD_CD = TDCACE.DATA[WS_VARIABLES.I-1].PROD_CD;
        WS_OUTPUT_DATA.AC_TERM = TDCACE.DATA[WS_VARIABLES.I-1].TERM;
        WS_OUTPUT_DATA.CCY = TDCACE.DATA[WS_VARIABLES.I-1].CCY;
        WS_OUTPUT_DATA.CCY_TYP = TDCACE.DATA[WS_VARIABLES.I-1].CCY_TYP;
        WS_OUTPUT_DATA.ISSU_DT = TDCACE.DATA[WS_VARIABLES.I-1].OPEN_DATE;
        WS_OUTPUT_DATA.ISSU_BR = TDCACE.DATA[WS_VARIABLES.I-1].OPEN_BR;
        WS_OUTPUT_DATA.ISSU_TLR = TDCACE.DATA[WS_VARIABLES.I-1].OPEN_TLR;
        WS_OUTPUT_DATA.SDT = TDCACE.DATA[WS_VARIABLES.I-1].SDT;
        WS_OUTPUT_DATA.DDT = TDCACE.DATA[WS_VARIABLES.I-1].DDT;
        WS_OUTPUT_DATA.INT_RATE = TDCACE.DATA[WS_VARIABLES.I-1].INT_RAT;
        WS_OUTPUT_DATA.INSTR_MTH = TDCACE.DATA[WS_VARIABLES.I-1].INSTR_MTH;
        WS_OUTPUT_DATA.RL_TERM = TDCACE.DATA[WS_VARIABLES.I-1].RL_TERM;
        WS_OUTPUT_DATA.NOR_NUM = TDCACE.DATA[WS_VARIABLES.I-1].NOR_NUM;
        WS_OUTPUT_DATA.STL_AC = TDCACE.DATA[WS_VARIABLES.I-1].STL_AC;
        WS_OUTPUT_DATA.PART_NUM = TDCACE.DATA[WS_VARIABLES.I-1].PART_NUM;
        if (TDCACE.DATA[WS_VARIABLES.I-1].ACO_STSW == null) TDCACE.DATA[WS_VARIABLES.I-1].ACO_STSW = "";
        JIBS_tmp_int = TDCACE.DATA[WS_VARIABLES.I-1].ACO_STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDCACE.DATA[WS_VARIABLES.I-1].ACO_STSW += " ";
        WS_OUTPUT_DATA.OFFICE_FRZ_FLG = TDCACE.DATA[WS_VARIABLES.I-1].ACO_STSW.substring(2 - 1, 2 + 1 - 1).charAt(0);
        if (TDCACE.DATA[WS_VARIABLES.I-1].ACO_STSW == null) TDCACE.DATA[WS_VARIABLES.I-1].ACO_STSW = "";
        JIBS_tmp_int = TDCACE.DATA[WS_VARIABLES.I-1].ACO_STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDCACE.DATA[WS_VARIABLES.I-1].ACO_STSW += " ";
        WS_OUTPUT_DATA.BANK_FRZ_FLG = TDCACE.DATA[WS_VARIABLES.I-1].ACO_STSW.substring(3 - 1, 3 + 1 - 1).charAt(0);
        if (TDCACE.DATA[WS_VARIABLES.I-1].ACO_STSW == null) TDCACE.DATA[WS_VARIABLES.I-1].ACO_STSW = "";
        JIBS_tmp_int = TDCACE.DATA[WS_VARIABLES.I-1].ACO_STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDCACE.DATA[WS_VARIABLES.I-1].ACO_STSW += " ";
        WS_OUTPUT_DATA.OFFICE_FBD_FLG = TDCACE.DATA[WS_VARIABLES.I-1].ACO_STSW.substring(8 - 1, 8 + 1 - 1).charAt(0);
        if (TDCACE.DATA[WS_VARIABLES.I-1].ACO_STSW == null) TDCACE.DATA[WS_VARIABLES.I-1].ACO_STSW = "";
        JIBS_tmp_int = TDCACE.DATA[WS_VARIABLES.I-1].ACO_STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDCACE.DATA[WS_VARIABLES.I-1].ACO_STSW += " ";
        WS_OUTPUT_DATA.BANK_FBD_FLG = TDCACE.DATA[WS_VARIABLES.I-1].ACO_STSW.substring(7 - 1, 7 + 1 - 1).charAt(0);
        WS_OUTPUT_DATA.OPEN_BAL = TDCACE.DATA[WS_VARIABLES.I-1].PBAL;
        WS_OUTPUT_DATA.CURR_BAL = TDCACE.DATA[WS_VARIABLES.I-1].BAL;
        WS_OUTPUT_DATA.AVL_BAL = TDCACE.DATA[WS_VARIABLES.I-1].KY_BAL;
        WS_OUTPUT_DATA.HOLD_BAL = TDCACE.DATA[WS_VARIABLES.I-1].HBAL;
        WS_OUTPUT_DATA.INT_SEL = TDCACE.DATA[WS_VARIABLES.I-1].INT_SEL;
        WS_OUTPUT_DATA.INT_RUL_CD = TDCACE.DATA[WS_VARIABLES.I-1].INT_RUL_CD;
        WS_OUTPUT_DATA.SPRD_PNT = TDCACE.DATA[WS_VARIABLES.I-1].SPRD_PNT;
        WS_OUTPUT_DATA.SPRD_PCT = TDCACE.DATA[WS_VARIABLES.I-1].SPRD_PCT;
        WS_OUTPUT_DATA.ACTI_NO = TDCACE.DATA[WS_VARIABLES.I-1].ACTI_NO;
        WS_OUTPUT_DATA.RUL_MSG = TDCACE.DATA[WS_VARIABLES.I-1].RUL_MSG;
    }
    public void B030_01_OUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.MAX_COL_NO = (short) MAX_COL;
        SCCMPAG.SCR_ROW_CNT = (short) MAX_ROW;
        SCCMPAG.SCR_COL_CNT = (short) COL_CNT;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B030_DATA_OUTPUT_MPAG() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_OUTPUT_DATA);
        SCCMPAG.DATA_LEN = 500;
        CEP.TRC(SCCGWA, SCCMPAG.DATA_LEN);
        B_MPAG();
        if (pgmRtn) return;
    }
    public void S000_CALL_DDZSQAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-DDZSQAC ", DDCSQAC, true);
    }
    public void S000_CALL_CIZQACRL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACRL", CICQACRL);
    }
    public void S000_CALL_DCZUCINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-CARD-INF       ", DCCUCINF, true);
        CEP.TRC(SCCGWA, DCCUCINF.RC);
        if (DCCUCINF.RC.RC_CODE != 0) {
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_TDZACE() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-AC-ENQ", TDCACE, true);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_VARIABLES.ERR_MSG);
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
