package com.hisun.LN;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNZBALHM {
    String JIBS_tmp_str[] = new String[10];
    DBParm LNTBALH_RD;
    boolean pgmRtn = false;
    char LNZBALHM_FILLER1 = ' ';
    char WS_UPDATE_FLG = ' ';
    char WS_FOUND_FLG = ' ';
    LNRBALH LNRBALH = new LNRBALH();
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCGWA SCCGWA;
    LNCBALHM LNCBALHM;
    public void MP(SCCGWA SCCGWA, LNCBALHM LNCBALHM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCBALHM = LNCBALHM;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZBALHM return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        LNCBALHM.RC.RC_APP = "LN";
        LNCBALHM.RC.RC_RTNCODE = 0;
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        B01_CHECK();
        if (pgmRtn) return;
        if (LNCBALHM.FUNC == '0') {
            B02_FUNC_WRITE();
            if (pgmRtn) return;
        } else if (LNCBALHM.FUNC == '1') {
            B03_FUNC_DELETE();
            if (pgmRtn) return;
        } else if (LNCBALHM.FUNC == '2') {
            B04_FUNC_REWRITE();
            if (pgmRtn) return;
        } else if (LNCBALHM.FUNC == '3') {
            B05_FUNC_READ();
            if (pgmRtn) return;
        } else if (LNCBALHM.FUNC == '4') {
            B06_FUNC_READ_UPDATE();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_FUNC_CODE, LNCBALHM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B01_CHECK() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCBALHM.REC_DATA.KEY);
        if (JIBS_tmp_str[0].trim().length() == 0 
            || JIBS_tmp_str[0].equalsIgnoreCase("0")) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_KEY_MUST_INPUT, LNCBALHM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B02_FUNC_WRITE() throws IOException,SQLException,Exception {
        WS_UPDATE_FLG = ' ';
        B10_READ();
        if (pgmRtn) return;
        if (WS_FOUND_FLG == 'Y') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_BALH_EXIST, LNCBALHM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        LNCBALHM.REC_DATA.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNCBALHM.REC_DATA.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        R00_COMA_RECA();
        if (pgmRtn) return;
        T00_WRITE_LNTBALH();
        if (pgmRtn) return;
    }
    public void B03_FUNC_DELETE() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCBALHM.REC_DATA.KEY);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNRBALH.KEY);
        T00_READUPDATE_LNTBALH();
        if (pgmRtn) return;
        if (WS_FOUND_FLG == 'Y') {
            T00_DELETE_LNTBALH();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_BALH_NOTFND, LNCBALHM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B04_FUNC_REWRITE() throws IOException,SQLException,Exception {
        LNCBALHM.REC_DATA.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNCBALHM.REC_DATA.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        R00_COMA_RECA();
        if (pgmRtn) return;
        T00_REWRITE_LNTBALH();
        if (pgmRtn) return;
    }
    public void B05_FUNC_READ() throws IOException,SQLException,Exception {
        WS_UPDATE_FLG = ' ';
        B10_READ();
        if (pgmRtn) return;
        if (WS_FOUND_FLG != 'Y') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_BALH_NOTFND, LNCBALHM.RC);
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
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_BALH_NOTFND, LNCBALHM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        R00_RECA_COMA();
        if (pgmRtn) return;
    }
    public void B10_READ() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRBALH);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCBALHM.REC_DATA.KEY);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNRBALH.KEY);
        if (WS_UPDATE_FLG == 'Y') {
            T00_READUPDATE_LNTBALH();
            if (pgmRtn) return;
        } else {
            T00_READ_LNTBALH();
            if (pgmRtn) return;
        }
    }
    public void R00_COMA_RECA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRBALH);
        LNRBALH.KEY.CONTRACT_NO = LNCBALHM.REC_DATA.KEY.CONTRACT_NO;
        LNRBALH.KEY.SUB_CTA_NO = LNCBALHM.REC_DATA.KEY.SUB_CTA_NO;
        LNRBALH.KEY.CTNR_NO = LNCBALHM.REC_DATA.KEY.CTNR_NO;
        LNRBALH.KEY.TXN_DT = LNCBALHM.REC_DATA.KEY.TXN_DT;
        LNRBALH.ID_FLG = LNCBALHM.REC_DATA.ID_FLG;
        LNRBALH.AMT = LNCBALHM.REC_DATA.AMT;
        LNRBALH.BAL = LNCBALHM.REC_DATA.BAL;
        LNRBALH.UPDTBL_DATE = LNCBALHM.REC_DATA.UPDTBL_DATE;
        LNRBALH.UPDTBL_TLR = LNCBALHM.REC_DATA.UPDTBL_TLR;
    }
    public void R00_RECA_COMA() throws IOException,SQLException,Exception {
        LNCBALHM.REC_DATA.KEY.CONTRACT_NO = LNRBALH.KEY.CONTRACT_NO;
        LNCBALHM.REC_DATA.KEY.SUB_CTA_NO = LNRBALH.KEY.SUB_CTA_NO;
        LNCBALHM.REC_DATA.KEY.CTNR_NO = LNRBALH.KEY.CTNR_NO;
        LNCBALHM.REC_DATA.KEY.TXN_DT = LNRBALH.KEY.TXN_DT;
        LNCBALHM.REC_DATA.ID_FLG = LNRBALH.ID_FLG;
        LNCBALHM.REC_DATA.AMT = LNRBALH.AMT;
        LNCBALHM.REC_DATA.BAL = LNRBALH.BAL;
        LNCBALHM.REC_DATA.UPDTBL_DATE = LNRBALH.UPDTBL_DATE;
        LNCBALHM.REC_DATA.UPDTBL_TLR = LNRBALH.UPDTBL_TLR;
        LNCBALHM.REC_DATA.TS = LNRBALH.TS;
    }
    public void T00_WRITE_LNTBALH() throws IOException,SQLException,Exception {
        LNTBALH_RD = new DBParm();
        LNTBALH_RD.TableName = "LNTBALH";
        IBS.WRITE(SCCGWA, LNRBALH, LNTBALH_RD);
    }
    public void T00_DELETE_LNTBALH() throws IOException,SQLException,Exception {
        LNTBALH_RD = new DBParm();
        LNTBALH_RD.TableName = "LNTBALH";
        IBS.DELETE(SCCGWA, LNRBALH, LNTBALH_RD);
    }
    public void T00_READ_LNTBALH() throws IOException,SQLException,Exception {
        LNTBALH_RD = new DBParm();
        LNTBALH_RD.TableName = "LNTBALH";
        IBS.READ(SCCGWA, LNRBALH, LNTBALH_RD);
        WS_FOUND_FLG = ' ';
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FOUND_FLG = 'Y';
        }
    }
    public void T00_READUPDATE_LNTBALH() throws IOException,SQLException,Exception {
        LNTBALH_RD = new DBParm();
        LNTBALH_RD.TableName = "LNTBALH";
        LNTBALH_RD.upd = true;
        IBS.READ(SCCGWA, LNRBALH, LNTBALH_RD);
        WS_FOUND_FLG = ' ';
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FOUND_FLG = 'Y';
        }
    }
    public void T00_REWRITE_LNTBALH() throws IOException,SQLException,Exception {
        LNTBALH_RD = new DBParm();
        LNTBALH_RD.TableName = "LNTBALH";
        IBS.REWRITE(SCCGWA, LNRBALH, LNTBALH_RD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
