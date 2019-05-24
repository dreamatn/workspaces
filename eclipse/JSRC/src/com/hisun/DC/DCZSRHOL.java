package com.hisun.DC;

import com.hisun.DD.*;
import com.hisun.SC.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZSRHOL {
    DBParm DDTHLD_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "DC771";
    String WS_ERR_MSG = " ";
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    DCCURHLD DCCURHLD = new DCCURHLD();
    DCCORHOL DCCORHOL = new DCCORHOL();
    DDRHLD DDRHLD = new DDRHLD();
    SCCGWA SCCGWA;
    DCCSRHOL DCCSRHOL;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, DCCSRHOL DCCSRHOL) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCSRHOL = DCCSRHOL;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZSRHOL return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_PROC();
        if (pgmRtn) return;
        B020_AC_REL_PROC();
        if (pgmRtn) return;
        B030_OUTPUT_DATA_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_PROC() throws IOException,SQLException,Exception {
        if (DCCSRHOL.DATA.HLD_NO.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_HLDNO_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCSRHOL.DATA.RHLD_TYP == ' ') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_RHLD_TYP_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCSRHOL.DATA.RHLD_TYP != '1' 
            && DCCSRHOL.DATA.RHLD_TYP != '2') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_RHLD_TYP_INVALID;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if ((DCCSRHOL.DATA.RHLD_TYP == '2' 
            && DCCSRHOL.DATA.RAMT == 0)) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_TRF_AMT_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP")) {
        } else {
            IBS.init(SCCGWA, DDRHLD);
            DDRHLD.KEY.HLD_NO = DCCSRHOL.DATA.HLD_NO;
            T000_READ_DDTHLD();
            if (pgmRtn) return;
            if (DDRHLD.HLD_CLS == '3' 
                || DDRHLD.HLD_CLS == '9') {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_HLD_CLS_NOT_RLS;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (DDRHLD.HLD_CLS != ' ' 
                && !DDRHLD.SPR_CHNL.equalsIgnoreCase(SCCGWA.COMM_AREA.REQ_SYSTEM) 
                && DDRHLD.SPR_CHNL.trim().length() > 0) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_SYSTEM_NOT_SAME);
            }
        }
    }
    public void B020_AC_REL_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCURHLD);
        DCCURHLD.DATA.HLD_NO = DCCSRHOL.DATA.HLD_NO;
        DCCURHLD.DATA.RHLD_TYP = DCCSRHOL.DATA.RHLD_TYP;
        DCCURHLD.DATA.RAMT = DCCSRHOL.DATA.RAMT;
        DCCURHLD.DATA.CHG_NO = DCCSRHOL.DATA.CHG_NO;
        DCCURHLD.DATA.SPR_NM = DCCSRHOL.DATA.SPR_NM;
        DCCURHLD.DATA.RSN = DCCSRHOL.DATA.RSN;
        DCCURHLD.DATA.RMK = DCCSRHOL.DATA.RMK;
        DCCURHLD.DATA.CHG_BR = DCCSRHOL.DATA.CHG_BR;
        DCCURHLD.DATA.LAW_NM1 = DCCSRHOL.DATA.LAW_NM1;
        DCCURHLD.DATA.LAW_NO1 = DCCSRHOL.DATA.LAW_NO1;
        DCCURHLD.DATA.LAW_NM2 = DCCSRHOL.DATA.LAW_NM2;
        DCCURHLD.DATA.LAW_NO2 = DCCSRHOL.DATA.LAW_NO2;
        S000_CALL_DCZURHLD();
        if (pgmRtn) return;
    }
    public void B030_OUTPUT_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCORHOL);
        DCCORHOL.DATA.HLD_NO = DCCURHLD.DATA.HLD_NO;
        DCCORHOL.DATA.AC = DCCURHLD.DATA.AC;
        DCCORHOL.DATA.HLD_TYP = DCCURHLD.DATA.HLD_TYP;
        DCCORHOL.DATA.SPR_TYP = DCCURHLD.DATA.SPR_TYP;
        DCCORHOL.DATA.CCY = DCCURHLD.DATA.CCY;
        DCCORHOL.DATA.CCY_TYP = DCCURHLD.DATA.CCY_TYP;
        DCCORHOL.DATA.BEF_AMT = DCCURHLD.DATA.BEF_AMT;
        DCCORHOL.DATA.EFF_DT = DCCURHLD.DATA.EFF_DT;
        DCCORHOL.DATA.EXP_DT = DCCURHLD.DATA.EXP_DT;
        DCCORHOL.DATA.RHLD_TYP = DCCURHLD.DATA.RHLD_TYP;
        DCCORHOL.DATA.RAMT = DCCURHLD.DATA.RAMT;
        DCCORHOL.DATA.REM_AMT = DCCURHLD.DATA.REM_AMT;
        DCCORHOL.DATA.CHG_NO = DCCURHLD.DATA.CHG_NO;
        DCCORHOL.DATA.SPR_NM = DCCURHLD.DATA.SPR_NM;
        DCCORHOL.DATA.RSN = DCCURHLD.DATA.RSN;
        DCCORHOL.DATA.RMK = DCCURHLD.DATA.RMK;
        DCCORHOL.DATA.CHG_BR = DCCURHLD.DATA.CHG_BR;
        DCCORHOL.DATA.LAW_NM1 = DCCURHLD.DATA.LAW_NM1;
        DCCORHOL.DATA.LAW_NO1 = DCCURHLD.DATA.LAW_NO1;
        DCCORHOL.DATA.LAW_NM2 = DCCURHLD.DATA.LAW_NM2;
        DCCORHOL.DATA.LAW_NO2 = DCCURHLD.DATA.LAW_NO2;
        DCCORHOL.DATA.CURR_BAL = DCCURHLD.DATA.CURR_BAL;
        DCCORHOL.DATA.AVL_AMT = DCCURHLD.DATA.AVL_AMT;
        DCCORHOL.DATA.ACC_HLD_AMT = DCCURHLD.DATA.ACC_HLD_AMT;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = DCCORHOL;
        SCCFMT.DATA_LEN = 1387;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_READ_DDTHLD() throws IOException,SQLException,Exception {
        DDTHLD_RD = new DBParm();
        DDTHLD_RD.TableName = "DDTHLD";
        IBS.READ(SCCGWA, DDRHLD, DDTHLD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_HLD_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZURHLD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-UNIT-RHLD", DCCURHLD);
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
