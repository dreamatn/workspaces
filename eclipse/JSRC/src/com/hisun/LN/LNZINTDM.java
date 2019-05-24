package com.hisun.LN;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNZINTDM {
    String JIBS_tmp_str[] = new String[10];
    DBParm LNTINTD_RD;
    boolean pgmRtn = false;
    char LNZINTDM_FILLER1 = ' ';
    char WS_UPDATE_FLG = ' ';
    char WS_FOUND_FLG = ' ';
    LNRINTD LNRINTD = new LNRINTD();
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCGWA SCCGWA;
    LNCINTDM LNCINTDM;
    public void MP(SCCGWA SCCGWA, LNCINTDM LNCINTDM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCINTDM = LNCINTDM;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZINTDM return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        LNCINTDM.RC.RC_APP = "LN";
        LNCINTDM.RC.RC_RTNCODE = 0;
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        B01_CHECK();
        if (pgmRtn) return;
        if (LNCINTDM.FUNC == '0') {
            B02_FUNC_WRITE();
            if (pgmRtn) return;
        } else if (LNCINTDM.FUNC == '1') {
            B03_FUNC_DELETE();
            if (pgmRtn) return;
        } else if (LNCINTDM.FUNC == '2') {
            B04_FUNC_REWRITE();
            if (pgmRtn) return;
        } else if (LNCINTDM.FUNC == '3') {
            B05_FUNC_READ();
            if (pgmRtn) return;
        } else if (LNCINTDM.FUNC == '4') {
            B06_FUNC_READ_UPDATE();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_FUNC_CODE, LNCINTDM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B01_CHECK() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCINTDM.REC_DATA.KEY);
        if (JIBS_tmp_str[0].trim().length() == 0 
            || JIBS_tmp_str[0].equalsIgnoreCase("0")) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_KEY_MUST_INPUT, LNCINTDM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B02_FUNC_WRITE() throws IOException,SQLException,Exception {
        WS_UPDATE_FLG = ' ';
        R00_READ();
        if (pgmRtn) return;
        if (WS_FOUND_FLG == 'Y') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_INTD_EXIST, LNCINTDM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        LNCINTDM.REC_DATA.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNCINTDM.REC_DATA.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNCINTDM.REC_DATA.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNCINTDM.REC_DATA.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        R00_COMA_RECA();
        if (pgmRtn) return;
        T00_WRITE_LNTINTD();
        if (pgmRtn) return;
    }
    public void B03_FUNC_DELETE() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCINTDM.REC_DATA.KEY);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNRINTD.KEY);
        T00_READUPDATE_LNTINTD();
        if (pgmRtn) return;
        if (WS_FOUND_FLG == 'Y') {
            T00_DELETE_LNTINTD();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_INTD_NOTFND, LNCINTDM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B04_FUNC_REWRITE() throws IOException,SQLException,Exception {
        LNCINTDM.REC_DATA.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNCINTDM.REC_DATA.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        R00_COMA_RECA();
        if (pgmRtn) return;
        T00_REWRITE_LNTINTD();
        if (pgmRtn) return;
    }
    public void B05_FUNC_READ() throws IOException,SQLException,Exception {
        WS_UPDATE_FLG = ' ';
        R00_READ();
        if (pgmRtn) return;
        if (WS_FOUND_FLG != 'Y') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_INTD_NOTFND, LNCINTDM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        R00_RECA_COMA();
        if (pgmRtn) return;
    }
    public void B06_FUNC_READ_UPDATE() throws IOException,SQLException,Exception {
        WS_UPDATE_FLG = 'Y';
        R00_READ();
        if (pgmRtn) return;
        if (WS_FOUND_FLG != 'Y') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_INTD_NOTFND, LNCINTDM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        R00_RECA_COMA();
        if (pgmRtn) return;
    }
    public void R00_READ() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRINTD);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCINTDM.REC_DATA.KEY);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNRINTD.KEY);
        if (WS_UPDATE_FLG == 'Y') {
            T00_READUPDATE_LNTINTD();
            if (pgmRtn) return;
        } else {
            T00_READ_LNTINTD();
            if (pgmRtn) return;
        }
    }
    public void R00_COMA_RECA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRINTD);
        LNRINTD.KEY.CONTRACT_NO = LNCINTDM.REC_DATA.KEY.CONTRACT_NO;
        LNRINTD.KEY.SUB_CTA_NO = LNCINTDM.REC_DATA.KEY.SUB_CTA_NO;
        LNRINTD.KEY.REPY_MTH = LNCINTDM.REC_DATA.KEY.REPY_MTH;
        LNRINTD.KEY.TERM = LNCINTDM.REC_DATA.KEY.TERM;
        LNRINTD.KEY.INT_TYP = LNCINTDM.REC_DATA.KEY.INT_TYP;
        LNRINTD.KEY.IRUL_SEQ = LNCINTDM.REC_DATA.KEY.IRUL_SEQ;
        LNRINTD.KEY.CTNR_NO = LNCINTDM.REC_DATA.KEY.CTNR_NO;
        LNRINTD.KEY.VAL_DT = LNCINTDM.REC_DATA.KEY.VAL_DT;
        LNRINTD.CUT_OFF_DT = LNCINTDM.REC_DATA.CUT_OFF_DT;
        LNRINTD.CALF_FML = LNCINTDM.REC_DATA.CALF_FML;
        LNRINTD.INT_AMT = LNCINTDM.REC_DATA.INT_AMT;
        LNRINTD.INT_RAT = LNCINTDM.REC_DATA.INT_RAT;
        LNRINTD.INT = LNCINTDM.REC_DATA.INT;
        LNRINTD.CRT_DATE = LNCINTDM.REC_DATA.CRT_DATE;
        LNRINTD.CRT_TLR = LNCINTDM.REC_DATA.CRT_TLR;
        LNRINTD.UPDTBL_DATE = LNCINTDM.REC_DATA.UPDTBL_DATE;
        LNRINTD.UPDTBL_TLR = LNCINTDM.REC_DATA.UPDTBL_TLR;
    }
    public void R00_RECA_COMA() throws IOException,SQLException,Exception {
        LNCINTDM.REC_DATA.KEY.CONTRACT_NO = LNRINTD.KEY.CONTRACT_NO;
        LNCINTDM.REC_DATA.KEY.SUB_CTA_NO = LNRINTD.KEY.SUB_CTA_NO;
        LNCINTDM.REC_DATA.KEY.REPY_MTH = LNRINTD.KEY.REPY_MTH;
        LNCINTDM.REC_DATA.KEY.TERM = LNRINTD.KEY.TERM;
        LNCINTDM.REC_DATA.KEY.INT_TYP = LNRINTD.KEY.INT_TYP;
        LNCINTDM.REC_DATA.KEY.IRUL_SEQ = LNRINTD.KEY.IRUL_SEQ;
        LNCINTDM.REC_DATA.KEY.CTNR_NO = LNRINTD.KEY.CTNR_NO;
        LNCINTDM.REC_DATA.KEY.VAL_DT = LNRINTD.KEY.VAL_DT;
        LNCINTDM.REC_DATA.CUT_OFF_DT = LNRINTD.CUT_OFF_DT;
        LNCINTDM.REC_DATA.CALF_FML = LNRINTD.CALF_FML;
        LNCINTDM.REC_DATA.INT_AMT = LNRINTD.INT_AMT;
        LNCINTDM.REC_DATA.INT_RAT = LNRINTD.INT_RAT;
        LNCINTDM.REC_DATA.INT = LNRINTD.INT;
        LNCINTDM.REC_DATA.CRT_DATE = LNRINTD.CRT_DATE;
        LNCINTDM.REC_DATA.CRT_TLR = LNRINTD.CRT_TLR;
        LNCINTDM.REC_DATA.UPDTBL_DATE = LNRINTD.UPDTBL_DATE;
        LNCINTDM.REC_DATA.UPDTBL_TLR = LNRINTD.UPDTBL_TLR;
        LNCINTDM.REC_DATA.TS = LNRINTD.TS;
    }
    public void T00_WRITE_LNTINTD() throws IOException,SQLException,Exception {
        LNTINTD_RD = new DBParm();
        LNTINTD_RD.TableName = "LNTINTD";
        IBS.WRITE(SCCGWA, LNRINTD, LNTINTD_RD);
    }
    public void T00_DELETE_LNTINTD() throws IOException,SQLException,Exception {
        LNTINTD_RD = new DBParm();
        LNTINTD_RD.TableName = "LNTINTD";
        IBS.DELETE(SCCGWA, LNRINTD, LNTINTD_RD);
    }
    public void T00_READ_LNTINTD() throws IOException,SQLException,Exception {
        LNTINTD_RD = new DBParm();
        LNTINTD_RD.TableName = "LNTINTD";
        IBS.READ(SCCGWA, LNRINTD, LNTINTD_RD);
        WS_FOUND_FLG = ' ';
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FOUND_FLG = 'Y';
        }
    }
    public void T00_READUPDATE_LNTINTD() throws IOException,SQLException,Exception {
        LNTINTD_RD = new DBParm();
        LNTINTD_RD.TableName = "LNTINTD";
        LNTINTD_RD.upd = true;
        IBS.READ(SCCGWA, LNRINTD, LNTINTD_RD);
        WS_FOUND_FLG = ' ';
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FOUND_FLG = 'Y';
        }
    }
    public void T00_REWRITE_LNTINTD() throws IOException,SQLException,Exception {
        LNTINTD_RD = new DBParm();
        LNTINTD_RD.TableName = "LNTINTD";
        IBS.REWRITE(SCCGWA, LNRINTD, LNTINTD_RD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (LNCINTDM.RC.RC_RTNCODE != 0) {
            CEP.TRC(SCCGWA, "LNCINTDM=");
            CEP.TRC(SCCGWA, LNCINTDM);
            CEP.TRC(SCCGWA, "LNRINTD =");
            CEP.TRC(SCCGWA, LNRINTD);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
