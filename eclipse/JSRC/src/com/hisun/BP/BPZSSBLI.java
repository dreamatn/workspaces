package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSSBLI {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_R_BRW_VSEL = "BP-R-BRW-VSEL ";
    int K_MAX_COL = 99;
    int K_MAX_ROW = 99;
    String WS_ERR_MSG = " ";
    int WS_CNT = 0;
    BPZSSBLI_WS_SBLI_HEAD WS_SBLI_HEAD = new BPZSSBLI_WS_SBLI_HEAD();
    BPZSSBLI_WS_SBLI_DETAIL WS_SBLI_DETAIL = new BPZSSBLI_WS_SBLI_DETAIL();
    int WS_TEST_BR = 0;
    BPZSSBLI_WS_TEMP_DETAIL WS_TEMP_DETAIL = new BPZSSBLI_WS_TEMP_DETAIL();
    char WS_TBL_VSEL_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    BPRVSEL BPRVSEL = new BPRVSEL();
    BPCRVSEB BPCRVSEB = new BPCRVSEB();
    SCCGWA SCCGWA;
    BPCSSBLI BPCSSBLI;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCSSBLI BPCSSBLI) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSSBLI = BPCSSBLI;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSSBLI return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCRVSEB);
        IBS.init(SCCGWA, BPRVSEL);
        IBS.init(SCCGWA, BPCSSBLI.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSSBLI);
        if (BPCSSBLI.FUNC == '0') {
            B010_BROWSE_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSSBLI.FUNC == '1') {
            B010_BROWSE_PROCESS();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B010_BROWSE_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSSBLI.BR);
        BPRVSEL.TX_BR = BPCSSBLI.BR;
        BPRVSEL.STS = 'Y';
        if (BPCSSBLI.TLR.trim().length() == 0) {
            BPRVSEL.TX_TLR = "%%%%%%%%";
        } else {
            BPRVSEL.TX_TLR = BPCSSBLI.TLR;
        }
        if (BPCSSBLI.CODE.trim().length() == 0) {
            BPRVSEL.BL_CODE = "%%%%%%";
        } else {
            BPRVSEL.BL_CODE = BPCSSBLI.CODE;
        }
        if (BPCSSBLI.HEAD_NO.trim().length() == 0) {
            BPRVSEL.HEAD_NO = "%%%%%%%%%%";
        } else {
            BPRVSEL.HEAD_NO = BPCSSBLI.HEAD_NO;
        }
        BPRVSEL.VALUE = BPCSSBLI.VALUE;
        BPCRVSEB.INFO.START_DT = BPCSSBLI.START_DT;
        BPCRVSEB.INFO.END_DT = BPCSSBLI.END_DT;
        BPCRVSEB.INFO.NO = BPCSSBLI.NO;
        if (BPCRVSEB.INFO.START_DT == 0) {
            BPCRVSEB.INFO.START_DT = 0;
        }
        if (BPCRVSEB.INFO.END_DT == 0) {
            if ("99991231".trim().length() == 0) BPCRVSEB.INFO.START_DT = 0;
            else BPCRVSEB.INFO.START_DT = Integer.parseInt("99991231");
        }
        if (BPCSSBLI.VALUE == 0 
            && !BPCRVSEB.INFO.NO.equalsIgnoreCase("0")) {
            BPCRVSEB.INFO.FUNC = '2';
        }
        if (BPCSSBLI.VALUE != 0 
            && !BPCRVSEB.INFO.NO.equalsIgnoreCase("0")) {
            BPCRVSEB.INFO.FUNC = '3';
        }
        if (BPCSSBLI.VALUE == 0 
            && BPCRVSEB.INFO.NO.equalsIgnoreCase("0")) {
            BPCRVSEB.INFO.FUNC = '4';
        }
        if (BPCSSBLI.VALUE != 0 
            && BPCRVSEB.INFO.NO.equalsIgnoreCase("0")) {
            BPCRVSEB.INFO.FUNC = '5';
        }
        BPCRVSEB.INFO.POINTER = BPRVSEL;
        BPCRVSEB.INFO.LEN = 152;
        S000_CALL_BPZRVSEB();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCSSBLI.BR);
        CEP.TRC(SCCGWA, BPCSSBLI.TLR);
        BPCRVSEB.INFO.FUNC = 'N';
        BPCRVSEB.INFO.POINTER = BPRVSEL;
        BPCRVSEB.INFO.LEN = 152;
        S000_CALL_BPZRVSEB();
        if (pgmRtn) return;
        if (BPCSSBLI.FUNC == '0') {
            B010_01_OUTPUT_TITLE();
            if (pgmRtn) return;
            for (WS_CNT = 1; BPCRVSEB.RETURN_INFO != 'N' 
                && WS_CNT <= 5000 
                && SCCMPAG.FUNC != 'E'; WS_CNT += 1) {
                B010_03_OUTPUT_DETAIL();
                if (pgmRtn) return;
                BPCRVSEB.INFO.FUNC = 'N';
                S000_CALL_BPZRVSEB();
                if (pgmRtn) return;
            }
        } else {
            WS_TEMP_DETAIL.WS_TEMP_DATE = BPRVSEL.KEY.TX_DATE;
            WS_TEMP_DETAIL.WS_TEMP_CODE = BPRVSEL.BL_CODE;
            WS_TEMP_DETAIL.WS_TEMP_VALUE = BPRVSEL.VALUE;
            WS_TEMP_DETAIL.WS_NUM = 0;
            B010_01_OUTPUT_TITLE();
            if (pgmRtn) return;
            WS_CNT = 0;
            for (WS_CNT = 1; BPCRVSEB.RETURN_INFO != 'N' 
                && WS_CNT <= 5000 
                && SCCMPAG.FUNC != 'E'; WS_CNT += 1) {
                if (BPRVSEL.KEY.TX_DATE != WS_TEMP_DETAIL.WS_TEMP_DATE 
                    || !BPRVSEL.BL_CODE.equalsIgnoreCase(WS_TEMP_DETAIL.WS_TEMP_CODE) 
                    || BPRVSEL.VALUE != WS_TEMP_DETAIL.WS_TEMP_VALUE) {
                    B010_04_OUTPUT_DETAIL();
                    if (pgmRtn) return;
                    WS_TEMP_DETAIL.WS_TEMP_DATE = BPRVSEL.KEY.TX_DATE;
                    WS_TEMP_DETAIL.WS_TEMP_CODE = BPRVSEL.BL_CODE;
                    WS_TEMP_DETAIL.WS_TEMP_VALUE = BPRVSEL.VALUE;
                    WS_TEMP_DETAIL.WS_NUM = BPRVSEL.NUM;
                } else {
                    WS_TEMP_DETAIL.WS_NUM = WS_TEMP_DETAIL.WS_NUM + BPRVSEL.NUM;
                }
                BPCRVSEB.INFO.FUNC = 'N';
                S000_CALL_BPZRVSEB();
                if (pgmRtn) return;
            }
            if (WS_CNT != 00001) {
                CEP.TRC(SCCGWA, "AAAAAAAAA");
                B010_04_OUTPUT_DETAIL();
                if (pgmRtn) return;
            }
        }
        BPCRVSEB.INFO.FUNC = 'E';
        BPCRVSEB.INFO.POINTER = BPCRVSEB;
        BPCRVSEB.INFO.LEN = 53;
        S000_CALL_BPZRVSEB();
        if (pgmRtn) return;
    }
    public void B010_01_OUTPUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 85;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        SCCMPAG.SCR_COL_CNT = 7;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B010_03_OUTPUT_DETAIL() throws IOException,SQLException,Exception {
        WS_SBLI_DETAIL.WS_SBLI_BR = BPRVSEL.TX_BR;
        WS_SBLI_DETAIL.WS_SBLI_DATE = BPRVSEL.KEY.TX_DATE;
        WS_SBLI_DETAIL.WS_SBLI_CODE = BPRVSEL.BL_CODE;
        WS_SBLI_DETAIL.WS_SBLI_VALUE = BPRVSEL.VALUE;
        WS_SBLI_DETAIL.WS_SBLI_BEG_NO = BPRVSEL.BEG_NO;
        WS_SBLI_DETAIL.WS_SBLI_END_NO = BPRVSEL.END_NO;
        WS_SBLI_DETAIL.WS_SBLI_NUM = BPRVSEL.NUM;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_SBLI_DETAIL);
        SCCMPAG.DATA_LEN = 85;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B010_04_OUTPUT_DETAIL() throws IOException,SQLException,Exception {
        WS_SBLI_DETAIL.WS_SBLI_BR = BPRVSEL.TX_BR;
        WS_SBLI_DETAIL.WS_SBLI_DATE = WS_TEMP_DETAIL.WS_TEMP_DATE;
        WS_SBLI_DETAIL.WS_SBLI_CODE = WS_TEMP_DETAIL.WS_TEMP_CODE;
        WS_SBLI_DETAIL.WS_SBLI_VALUE = WS_TEMP_DETAIL.WS_TEMP_VALUE;
        WS_SBLI_DETAIL.WS_SBLI_NUM = WS_TEMP_DETAIL.WS_NUM;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_SBLI_DETAIL);
        SCCMPAG.DATA_LEN = 85;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_CALL_BPZRVSEB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_BRW_VSEL, BPCRVSEB);
        CEP.TRC(SCCGWA, BPCRVSEB.RC);
        CEP.TRC(SCCGWA, BPCRVSEB.RETURN_INFO);
        if (BPCRVSEB.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRVSEB.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
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
