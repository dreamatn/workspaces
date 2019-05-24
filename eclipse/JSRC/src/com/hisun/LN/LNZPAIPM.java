package com.hisun.LN;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNZPAIPM {
    String JIBS_tmp_str[] = new String[10];
    DBParm LNTPAIP_RD;
    boolean pgmRtn = false;
    char LNZPAIPM_FILLER1 = ' ';
    char WS_UPDATE_FLG = ' ';
    char WS_FOUND_FLG = ' ';
    LNRPAIP LNRPAIP = new LNRPAIP();
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCGWA SCCGWA;
    LNCPAIPM LNCPAIPM;
    public void MP(SCCGWA SCCGWA, LNCPAIPM LNCPAIPM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCPAIPM = LNCPAIPM;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZPAIPM return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        LNCPAIPM.RC.RC_APP = "LN";
        LNCPAIPM.RC.RC_RTNCODE = 0;
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        B01_CHECK();
        if (pgmRtn) return;
        if (LNCPAIPM.FUNC == '0') {
            B02_FUNC_WRITE();
            if (pgmRtn) return;
        } else if (LNCPAIPM.FUNC == '1') {
            B03_FUNC_DELETE();
            if (pgmRtn) return;
        } else if (LNCPAIPM.FUNC == '2') {
            B04_FUNC_REWRITE();
            if (pgmRtn) return;
        } else if (LNCPAIPM.FUNC == '3') {
            B05_FUNC_READ();
            if (pgmRtn) return;
        } else if (LNCPAIPM.FUNC == '4') {
            B06_FUNC_READ_UPDATE();
            if (pgmRtn) return;
        } else if (LNCPAIPM.FUNC == '5') {
            B07_FUNC_READ_LAST();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_FUNC_CODE, LNCPAIPM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B01_CHECK() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCPAIPM.REC_DATA.KEY);
        if (JIBS_tmp_str[0].trim().length() == 0 
            || JIBS_tmp_str[0].equalsIgnoreCase("0")) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_KEY_MUST_INPUT, LNCPAIPM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B02_FUNC_WRITE() throws IOException,SQLException,Exception {
        WS_UPDATE_FLG = ' ';
        B10_READ();
        if (pgmRtn) return;
        if (WS_FOUND_FLG == 'Y') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_PAIP_EXIST, LNCPAIPM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        R00_COMA_RECA();
        if (pgmRtn) return;
        T00_WRITE_LNTPAIP();
        if (pgmRtn) return;
    }
    public void B03_FUNC_DELETE() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCPAIPM.REC_DATA.KEY);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNRPAIP.KEY);
        T00_READUPDATE_LNTPAIP();
        if (pgmRtn) return;
        if (WS_FOUND_FLG == 'Y') {
            T00_DELETE_LNTPAIP();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_PAIP_NOTFND, LNCPAIPM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B04_FUNC_REWRITE() throws IOException,SQLException,Exception {
        R00_COMA_RECA();
        if (pgmRtn) return;
        T00_REWRITE_LNTPAIP();
        if (pgmRtn) return;
    }
    public void B07_FUNC_READ_LAST() throws IOException,SQLException,Exception {
        R00_COMA_RECA();
        if (pgmRtn) return;
        T00_READ_LNTPAIP_LAST();
        if (pgmRtn) return;
    }
    public void B05_FUNC_READ() throws IOException,SQLException,Exception {
        WS_UPDATE_FLG = ' ';
        B10_READ();
        if (pgmRtn) return;
        if (WS_FOUND_FLG != 'Y') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_PAIP_NOTFND, LNCPAIPM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        R00_RECA_COMA();
        if (pgmRtn) return;
    }
    public void B06_FUNC_READ_UPDATE() throws IOException,SQLException,Exception {
        WS_UPDATE_FLG = 'Y';
        B10_READ();
        if (pgmRtn) return;
        if (WS_FOUND_FLG != 'Y') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_PAIP_NOTFND, LNCPAIPM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        R00_RECA_COMA();
        if (pgmRtn) return;
    }
    public void B10_READ() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRPAIP);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCPAIPM.REC_DATA.KEY);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNRPAIP.KEY);
        if (WS_UPDATE_FLG == 'Y') {
            T00_READUPDATE_LNTPAIP();
            if (pgmRtn) return;
        } else {
            T00_READ_LNTPAIP();
            if (pgmRtn) return;
        }
    }
    public void R00_COMA_RECA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRPAIP);
        LNRPAIP.KEY.CONTRACT_NO = LNCPAIPM.REC_DATA.KEY.CONTRACT_NO;
        LNRPAIP.KEY.SUB_CTA_NO = LNCPAIPM.REC_DATA.KEY.SUB_CTA_NO;
        LNRPAIP.KEY.PHS_NO = LNCPAIPM.REC_DATA.KEY.PHS_NO;
        LNRPAIP.INST_MTH = LNCPAIPM.REC_DATA.INST_MTH;
        LNRPAIP.PERD = LNCPAIPM.REC_DATA.PERD;
        LNRPAIP.PERD_UNIT = LNCPAIPM.REC_DATA.PERD_UNIT;
        LNRPAIP.PHS_INST_AMT = LNCPAIPM.REC_DATA.PHS_INST_AMT;
        LNRPAIP.PHS_PRIN_AMT = LNCPAIPM.REC_DATA.PHS_PRIN_AMT;
        LNRPAIP.PHS_TOT_TERM = LNCPAIPM.REC_DATA.PHS_TOT_TERM;
        LNRPAIP.PHS_REM_PRIN_AMT = LNCPAIPM.REC_DATA.PHS_REM_PRIN_AMT;
        LNRPAIP.PHS_CAL_TERM = LNCPAIPM.REC_DATA.PHS_CAL_TERM;
        LNRPAIP.PHS_CMP_TERM = LNCPAIPM.REC_DATA.PHS_CMP_TERM;
        LNRPAIP.CUR_INST_AMT = LNCPAIPM.REC_DATA.CUR_INST_AMT;
        LNRPAIP.CUR_INST_IRAT = LNCPAIPM.REC_DATA.CUR_INST_IRAT;
        LNRPAIP.CRT_DATE = LNCPAIPM.REC_DATA.CRT_DATE;
        LNRPAIP.CRT_TLR = LNCPAIPM.REC_DATA.CRT_TLR;
        LNRPAIP.UPDTBL_DATE = LNCPAIPM.REC_DATA.UPDTBL_DATE;
        LNRPAIP.UPDTBL_TLR = LNCPAIPM.REC_DATA.UPDTBL_TLR;
    }
    public void R00_RECA_COMA() throws IOException,SQLException,Exception {
        LNCPAIPM.REC_DATA.KEY.CONTRACT_NO = LNRPAIP.KEY.CONTRACT_NO;
        LNCPAIPM.REC_DATA.KEY.SUB_CTA_NO = LNRPAIP.KEY.SUB_CTA_NO;
        LNCPAIPM.REC_DATA.KEY.PHS_NO = LNRPAIP.KEY.PHS_NO;
        LNCPAIPM.REC_DATA.INST_MTH = LNRPAIP.INST_MTH;
        LNCPAIPM.REC_DATA.PERD = LNRPAIP.PERD;
        LNCPAIPM.REC_DATA.PERD_UNIT = LNRPAIP.PERD_UNIT;
        LNCPAIPM.REC_DATA.PHS_INST_AMT = LNRPAIP.PHS_INST_AMT;
        LNCPAIPM.REC_DATA.PHS_PRIN_AMT = LNRPAIP.PHS_PRIN_AMT;
        LNCPAIPM.REC_DATA.PHS_TOT_TERM = LNRPAIP.PHS_TOT_TERM;
        LNCPAIPM.REC_DATA.PHS_REM_PRIN_AMT = LNRPAIP.PHS_REM_PRIN_AMT;
        LNCPAIPM.REC_DATA.PHS_CAL_TERM = LNRPAIP.PHS_CAL_TERM;
        LNCPAIPM.REC_DATA.PHS_CMP_TERM = LNRPAIP.PHS_CMP_TERM;
        LNCPAIPM.REC_DATA.CUR_INST_AMT = LNRPAIP.CUR_INST_AMT;
        LNCPAIPM.REC_DATA.CUR_INST_IRAT = LNRPAIP.CUR_INST_IRAT;
        LNCPAIPM.REC_DATA.CRT_DATE = LNRPAIP.CRT_DATE;
        LNCPAIPM.REC_DATA.CRT_TLR = LNRPAIP.CRT_TLR;
        LNCPAIPM.REC_DATA.UPDTBL_DATE = LNRPAIP.UPDTBL_DATE;
        LNCPAIPM.REC_DATA.UPDTBL_TLR = LNRPAIP.UPDTBL_TLR;
        LNCPAIPM.REC_DATA.TS = LNRPAIP.TS;
    }
    public void T00_WRITE_LNTPAIP() throws IOException,SQLException,Exception {
        LNRPAIP.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRPAIP.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRPAIP.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNRPAIP.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTPAIP_RD = new DBParm();
        LNTPAIP_RD.TableName = "LNTPAIP";
        IBS.WRITE(SCCGWA, LNRPAIP, LNTPAIP_RD);
    }
    public void T00_DELETE_LNTPAIP() throws IOException,SQLException,Exception {
        LNTPAIP_RD = new DBParm();
        LNTPAIP_RD.TableName = "LNTPAIP";
        IBS.DELETE(SCCGWA, LNRPAIP, LNTPAIP_RD);
    }
    public void T00_READ_LNTPAIP() throws IOException,SQLException,Exception {
        LNTPAIP_RD = new DBParm();
        LNTPAIP_RD.TableName = "LNTPAIP";
        IBS.READ(SCCGWA, LNRPAIP, LNTPAIP_RD);
        WS_FOUND_FLG = ' ';
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FOUND_FLG = 'Y';
        }
    }
    public void T00_READ_LNTPAIP_LAST() throws IOException,SQLException,Exception {
        LNTPAIP_RD = new DBParm();
        LNTPAIP_RD.TableName = "LNTPAIP";
        LNTPAIP_RD.fst = true;
        LNTPAIP_RD.order = "PHS_NO DESC";
        IBS.READ(SCCGWA, LNRPAIP, LNTPAIP_RD);
        WS_FOUND_FLG = ' ';
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FOUND_FLG = 'Y';
        }
    }
    public void T00_READUPDATE_LNTPAIP() throws IOException,SQLException,Exception {
        LNTPAIP_RD = new DBParm();
        LNTPAIP_RD.TableName = "LNTPAIP";
        LNTPAIP_RD.upd = true;
        IBS.READ(SCCGWA, LNRPAIP, LNTPAIP_RD);
        WS_FOUND_FLG = ' ';
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FOUND_FLG = 'Y';
        }
    }
    public void T00_REWRITE_LNTPAIP() throws IOException,SQLException,Exception {
        LNRPAIP.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRPAIP.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTPAIP_RD = new DBParm();
        LNTPAIP_RD.TableName = "LNTPAIP";
        IBS.REWRITE(SCCGWA, LNRPAIP, LNTPAIP_RD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (LNCPAIPM.RC.RC_RTNCODE != 0) {
            CEP.TRC(SCCGWA, "LNCPAIPM=");
            CEP.TRC(SCCGWA, LNCPAIPM);
            CEP.TRC(SCCGWA, "LNRPAIP =");
            CEP.TRC(SCCGWA, LNRPAIP);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
