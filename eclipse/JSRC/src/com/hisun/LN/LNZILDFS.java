package com.hisun.LN;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNZILDFS {
    boolean pgmRtn = false;
    char WS_CI_BREACH_TYP = ' ';
    char WS_CTA_OVERDUE_TYP = ' ';
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCGWA SCCGWA;
    LNCILDFS LNCILDFS;
    public void MP(SCCGWA SCCGWA, LNCILDFS LNCILDFS) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCILDFS = LNCILDFS;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZILDFS return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        LNCILDFS.RC.RC_MMO = "LN";
        LNCILDFS.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B030_DEF_STATUE_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNCILDFS.INPUT_AREA.CI_BREACH_TYP);
        CEP.TRC(SCCGWA, LNCILDFS.INPUT_AREA.CTA_OVERDUE_TYP);
        WS_CI_BREACH_TYP = LNCILDFS.INPUT_AREA.CI_BREACH_TYP;
        if ((WS_CI_BREACH_TYP != '0' 
            && WS_CI_BREACH_TYP != '1' 
            && WS_CI_BREACH_TYP != '2' 
            && WS_CI_BREACH_TYP != '3' 
            && WS_CI_BREACH_TYP != '4' 
            && WS_CI_BREACH_TYP != '5' 
            && WS_CI_BREACH_TYP != '6' 
            && WS_CI_BREACH_TYP != '7' 
            && WS_CI_BREACH_TYP != '8')) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_CI_BREACH_TYP, LNCILDFS.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        WS_CTA_OVERDUE_TYP = LNCILDFS.INPUT_AREA.CTA_OVERDUE_TYP;
        if ((WS_CTA_OVERDUE_TYP != '0' 
            && WS_CTA_OVERDUE_TYP != '1' 
            && WS_CTA_OVERDUE_TYP != '2')) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_CTA_OVERDUE_TYP, LNCILDFS.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B030_DEF_STATUE_PROC() throws IOException,SQLException,Exception {
        LNCILDFS.OUTPUT_AREA.LN_DEFAULT_STS = '3';
        if (LNCILDFS.INPUT_AREA.CI_BREACH_TYP == '0') {
            LNCILDFS.OUTPUT_AREA.LN_DEFAULT_STS = '1';
        }
        if (LNCILDFS.INPUT_AREA.CTA_OVERDUE_TYP == '0') {
            if (LNCILDFS.INPUT_AREA.CI_BREACH_TYP == '1' 
                || LNCILDFS.INPUT_AREA.CI_BREACH_TYP == '2' 
                || LNCILDFS.INPUT_AREA.CI_BREACH_TYP == '7') {
                LNCILDFS.OUTPUT_AREA.LN_DEFAULT_STS = '2';
            }
        }
        if ((LNCILDFS.INPUT_AREA.CTA_OVERDUE_TYP == '2' 
            && LNCILDFS.INPUT_AREA.CI_BREACH_TYP != '0') 
            || (LNCILDFS.INPUT_AREA.CI_BREACH_TYP == '6' 
            || LNCILDFS.INPUT_AREA.CI_BREACH_TYP == '8')) {
            LNCILDFS.OUTPUT_AREA.LN_DEFAULT_STS = '4';
        }
        CEP.TRC(SCCGWA, LNCILDFS.OUTPUT_AREA.LN_DEFAULT_STS);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (LNCILDFS.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "LNCILDFS=");
            CEP.TRC(SCCGWA, LNCILDFS);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
