package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZPCSDT {
    String JIBS_tmp_str[] = new String[10];
    String JIBS_NumStr;
    String JIBS_f0;
    int JIBS_tmp_int;
    boolean pgmRtn = false;
    String PGM_SCSSCLDT = "SCSSCLDT";
    short WK_1 = 1;
    short WK_7 = 7;
    short WK_10 = 10;
    short WK_MMDD = 0229;
    int WS_DAYS = 0;
    short WS_MONTHS = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCCLDT SCCCLDT = new SCCCLDT();
    SCCGWA SCCGWA;
    BPCPCSDT BPCPCSDT;
    public void MP(SCCGWA SCCGWA, BPCPCSDT BPCPCSDT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCPCSDT = BPCPCSDT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZPCSDT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCLDT);
        IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NORMAL, BPCPCSDT.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        B200_MAIN_PROCESS();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if ((BPCPCSDT.INPUT.STOR_FRY == ' ' 
            || BPCPCSDT.INPUT.STOR_FRY == '0') 
            || (BPCPCSDT.INPUT.AC_DATE == ' ' 
            || BPCPCSDT.INPUT.AC_DATE == 0)) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, BPCPCSDT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCPCSDT.INPUT.STOR_FRY == 'M' 
            && (BPCPCSDT.INPUT.SPLIT_FLAG == ' ' 
            || BPCPCSDT.INPUT.SPLIT_FLAG == '0')) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, BPCPCSDT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCPCSDT.INPUT.STOR_FRY == 'M' 
            || BPCPCSDT.INPUT.STOR_FRY == 'Y') {
            if (BPCPCSDT.INPUT.STOR_CYC == ' ' 
                || BPCPCSDT.INPUT.STOR_CYC == 0) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, BPCPCSDT.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void B200_MAIN_PROCESS() throws IOException,SQLException,Exception {
        if (BPCPCSDT.INPUT.STOR_FRY == 'D') {
            B210_DAY_PROCESS();
            if (pgmRtn) return;
        } else if (BPCPCSDT.INPUT.STOR_FRY == 'M') {
            B240_MONTH_PROCESS();
            if (pgmRtn) return;
        } else if (BPCPCSDT.INPUT.STOR_FRY == 'Y') {
            B270_YEAR_PROCESS();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, BPCPCSDT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B210_DAY_PROCESS() throws IOException,SQLException,Exception {
        if (BPCPCSDT.INPUT.STOR_CYC == 0) {
            BPCPCSDT.INPUT.AC_DATE = BPCPCSDT.INPUT.AC_DATE + 1;
            BPCPCSDT.OUTPUT.STOR_DATE = BPCPCSDT.INPUT.AC_DATE;
        } else if (BPCPCSDT.INPUT.STOR_CYC == WK_1) {
            BPCPCSDT.OUTPUT.STOR_DATE = BPCPCSDT.INPUT.AC_DATE;
        } else {
            WS_DAYS = ( BPCPCSDT.INPUT.STOR_CYC - 1 ) * ( -1 );
            R000_GET_STRO_DATE();
            if (pgmRtn) return;
        }
    }
    public void B240_MONTH_PROCESS() throws IOException,SQLException,Exception {
        WS_MONTHS = (short) (( BPCPCSDT.INPUT.STOR_CYC ) * ( -1 ));
        R000_GET_STRO_DATE();
        if (pgmRtn) return;
        if (BPCPCSDT.INPUT.SPLIT_FLAG == 'N') {
            JIBS_tmp_str[0] = "" + BPCPCSDT.OUTPUT.STOR_DATE;
            JIBS_f0 = "";
            for (int i=0;i<8-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
            JIBS_NumStr = JIBS_f0 + BPCPCSDT.OUTPUT.STOR_DATE;
            JIBS_tmp_str[1] = "" + 1;
            JIBS_tmp_int = JIBS_tmp_str[1].length();
            for (int i=0;i<2-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
            JIBS_NumStr = JIBS_NumStr.substring(0, 7 - 1) + JIBS_tmp_str[1] + JIBS_NumStr.substring(7 + 2 - 1);
            BPCPCSDT.OUTPUT.STOR_DATE = Integer.parseInt(JIBS_NumStr);
        }
    }
    public void B270_YEAR_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_MONTHS);
        CEP.TRC(SCCGWA, BPCPCSDT.INPUT.STOR_CYC);
        WS_MONTHS = (short) (( BPCPCSDT.INPUT.STOR_CYC ) * ( -1 ) * 12);
        CEP.TRC(SCCGWA, WS_MONTHS);
        CEP.TRC(SCCGWA, BPCPCSDT.INPUT.STOR_CYC);
        R000_GET_STRO_DATE();
        if (pgmRtn) return;
    }
    public void R000_GET_STRO_DATE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCLDT);
        SCCCLDT.DATE1 = BPCPCSDT.INPUT.AC_DATE;
        if (BPCPCSDT.INPUT.STOR_FRY == 'D') {
            SCCCLDT.DAYS = WS_DAYS;
        } else if (BPCPCSDT.INPUT.STOR_FRY == 'M') {
            CEP.TRC(SCCGWA, " BY  MONTH");
            SCCCLDT.MTHS = WS_MONTHS;
        } else if (BPCPCSDT.INPUT.STOR_FRY == 'Y') {
            CEP.TRC(SCCGWA, " BY  YEAR ");
            SCCCLDT.MTHS = WS_MONTHS;
        }
        CEP.TRC(SCCGWA, SCCCLDT.DATE1);
        CEP.TRC(SCCGWA, SCCCLDT.DAYS);
        CEP.TRC(SCCGWA, SCCCLDT.MTHS);
        S000_CALL_SCSSCLDT();
        if (pgmRtn) return;
        BPCPCSDT.OUTPUT.STOR_DATE = SCCCLDT.DATE2;
    }
    public void S000_CALL_SCSSCLDT() throws IOException,SQLException,Exception {
        SCSSCLDT SCSSCLDT1 = new SCSSCLDT();
        SCSSCLDT1.MP(SCCGWA, SCCCLDT);
        if (0 != 0 
            || SCCCLDT.RC != 0) {
            CEP.TRC(SCCGWA, "CALL SCSSCLDT ERROR");
            CEP.TRC(SCCGWA, SCCCLDT.RC);
            BPCPCSDT.RC.RC_MMO = "SC";
            BPCPCSDT.RC.RC_CODE = SCCCLDT.RC;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCPCSDT.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCPCSDT = ");
            CEP.TRC(SCCGWA, BPCPCSDT);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
