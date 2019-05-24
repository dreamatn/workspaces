package com.hisun.LN;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNZPLPIM {
    String JIBS_tmp_str[] = new String[10];
    DBParm LNTPLPI_RD;
    boolean pgmRtn = false;
    char LNZPLPIM_FILLER1 = ' ';
    char WS_UPDATE_FLG = ' ';
    char WS_FOUND_FLG = ' ';
    LNRPLPI LNRPLPI = new LNRPLPI();
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCGWA SCCGWA;
    LNCPLPIM LNCPLPIM;
    public void MP(SCCGWA SCCGWA, LNCPLPIM LNCPLPIM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCPLPIM = LNCPLPIM;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "123456");
        CEP.TRC(SCCGWA, "LNZPLPIM return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        LNCPLPIM.RC.RC_APP = "LN";
        LNCPLPIM.RC.RC_RTNCODE = 0;
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        B01_CHECK();
        if (pgmRtn) return;
        if (LNCPLPIM.FUNC == '0') {
            B02_FUNC_WRITE();
            if (pgmRtn) return;
        } else if (LNCPLPIM.FUNC == '1') {
            B03_FUNC_DELETE();
            if (pgmRtn) return;
        } else if (LNCPLPIM.FUNC == '2') {
            B04_FUNC_REWRITE();
            if (pgmRtn) return;
        } else if (LNCPLPIM.FUNC == '3') {
            B05_FUNC_READ();
            if (pgmRtn) return;
        } else if (LNCPLPIM.FUNC == '4') {
            B06_FUNC_READ_UPDATE();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_FUNC_CODE, LNCPLPIM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B01_CHECK() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCPLPIM.REC_DATA.KEY);
        if (JIBS_tmp_str[0].trim().length() == 0 
            || JIBS_tmp_str[0].equalsIgnoreCase("0")) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_KEY_MUST_INPUT, LNCPLPIM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B02_FUNC_WRITE() throws IOException,SQLException,Exception {
        WS_UPDATE_FLG = ' ';
        B10_READ();
        if (pgmRtn) return;
        if (WS_FOUND_FLG == 'Y') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_PLPI_EXIST, LNCPLPIM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        LNCPLPIM.REC_DATA.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNCPLPIM.REC_DATA.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNCPLPIM.REC_DATA.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNCPLPIM.REC_DATA.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        R00_COMA_RECA();
        if (pgmRtn) return;
        T00_WRITE_LNTPLPI();
        if (pgmRtn) return;
    }
    public void B03_FUNC_DELETE() throws IOException,SQLException,Exception {
        LNRPLPI.KEY.CONTRACT_NO = LNCPLPIM.REC_DATA.KEY.CONTRACT_NO;
        LNRPLPI.KEY.SUB_CTA_NO = LNCPLPIM.REC_DATA.KEY.SUB_CTA_NO;
        LNRPLPI.KEY.REPY_MTH = LNCPLPIM.REC_DATA.KEY.REPY_MTH;
        LNRPLPI.KEY.TERM = LNCPLPIM.REC_DATA.KEY.TERM;
        T00_READUPDATE_LNTPLPI();
        if (pgmRtn) return;
        if (WS_FOUND_FLG == 'Y') {
            T00_DELETE_LNTPLPI();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_PLPI_NOTFND, LNCPLPIM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B04_FUNC_REWRITE() throws IOException,SQLException,Exception {
        LNCPLPIM.REC_DATA.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNCPLPIM.REC_DATA.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        R00_COMA_RECA();
        if (pgmRtn) return;
        T00_REWRITE_LNTPLPI();
        if (pgmRtn) return;
    }
    public void B05_FUNC_READ() throws IOException,SQLException,Exception {
        WS_UPDATE_FLG = ' ';
        B10_READ();
        if (pgmRtn) return;
        if (WS_FOUND_FLG != 'Y') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_PLPI_NOTFND, LNCPLPIM.RC);
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
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_PLPI_NOTFND, LNCPLPIM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        R00_RECA_COMA();
        if (pgmRtn) return;
    }
    public void B10_READ() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRPLPI);
        LNRPLPI.KEY.CONTRACT_NO = LNCPLPIM.REC_DATA.KEY.CONTRACT_NO;
        LNRPLPI.KEY.SUB_CTA_NO = LNCPLPIM.REC_DATA.KEY.SUB_CTA_NO;
        LNRPLPI.KEY.REPY_MTH = LNCPLPIM.REC_DATA.KEY.REPY_MTH;
        LNRPLPI.KEY.TERM = LNCPLPIM.REC_DATA.KEY.TERM;
        if (WS_UPDATE_FLG == 'Y') {
            T00_READUPDATE_LNTPLPI();
            if (pgmRtn) return;
        } else {
            T00_READ_LNTPLPI();
            if (pgmRtn) return;
        }
    }
    public void R00_COMA_RECA() throws IOException,SQLException,Exception {
        LNRPLPI.KEY.CONTRACT_NO = LNCPLPIM.REC_DATA.KEY.CONTRACT_NO;
        LNRPLPI.KEY.SUB_CTA_NO = LNCPLPIM.REC_DATA.KEY.SUB_CTA_NO;
        LNRPLPI.KEY.REPY_MTH = LNCPLPIM.REC_DATA.KEY.REPY_MTH;
        LNRPLPI.KEY.TERM = LNCPLPIM.REC_DATA.KEY.TERM;
        LNRPLPI.VAL_DT = LNCPLPIM.REC_DATA.VAL_DT;
        LNRPLPI.DUE_DT = LNCPLPIM.REC_DATA.DUE_DT;
        LNRPLPI.REC_STS = LNCPLPIM.REC_DATA.REC_STS;
        LNRPLPI.DUE_REPY_AMT = LNCPLPIM.REC_DATA.DUE_REPY_AMT;
        LNRPLPI.PRIN_AMT = LNCPLPIM.REC_DATA.PRIN_AMT;
        LNRPLPI.REMARK = LNCPLPIM.REC_DATA.REMARK;
        LNRPLPI.UPDTBL_DATE = LNCPLPIM.REC_DATA.UPDTBL_DATE;
        LNRPLPI.UPDTBL_TLR = LNCPLPIM.REC_DATA.UPDTBL_TLR;
    }
    public void R00_RECA_COMA() throws IOException,SQLException,Exception {
        LNCPLPIM.REC_DATA.KEY.CONTRACT_NO = LNRPLPI.KEY.CONTRACT_NO;
        LNCPLPIM.REC_DATA.KEY.SUB_CTA_NO = LNRPLPI.KEY.SUB_CTA_NO;
        LNCPLPIM.REC_DATA.KEY.REPY_MTH = LNRPLPI.KEY.REPY_MTH;
        LNCPLPIM.REC_DATA.KEY.TERM = LNRPLPI.KEY.TERM;
        LNCPLPIM.REC_DATA.VAL_DT = LNRPLPI.VAL_DT;
        LNCPLPIM.REC_DATA.DUE_DT = LNRPLPI.DUE_DT;
        LNCPLPIM.REC_DATA.REC_STS = LNRPLPI.REC_STS;
        LNCPLPIM.REC_DATA.DUE_REPY_AMT = LNRPLPI.DUE_REPY_AMT;
        LNCPLPIM.REC_DATA.PRIN_AMT = LNRPLPI.PRIN_AMT;
        LNCPLPIM.REC_DATA.REMARK = LNRPLPI.REMARK;
        LNCPLPIM.REC_DATA.UPDTBL_DATE = LNRPLPI.UPDTBL_DATE;
        LNCPLPIM.REC_DATA.UPDTBL_TLR = LNRPLPI.UPDTBL_TLR;
        LNCPLPIM.REC_DATA.TS = LNRPLPI.TS;
    }
    public void T00_WRITE_LNTPLPI() throws IOException,SQLException,Exception {
        LNTPLPI_RD = new DBParm();
        LNTPLPI_RD.TableName = "LNTPLPI";
        IBS.WRITE(SCCGWA, LNRPLPI, LNTPLPI_RD);
    }
    public void T00_DELETE_LNTPLPI() throws IOException,SQLException,Exception {
        LNTPLPI_RD = new DBParm();
        LNTPLPI_RD.TableName = "LNTPLPI";
        IBS.DELETE(SCCGWA, LNRPLPI, LNTPLPI_RD);
    }
    public void T00_READ_LNTPLPI() throws IOException,SQLException,Exception {
        LNTPLPI_RD = new DBParm();
        LNTPLPI_RD.TableName = "LNTPLPI";
        IBS.READ(SCCGWA, LNRPLPI, LNTPLPI_RD);
        WS_FOUND_FLG = ' ';
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FOUND_FLG = 'Y';
        }
    }
    public void T00_READUPDATE_LNTPLPI() throws IOException,SQLException,Exception {
        LNTPLPI_RD = new DBParm();
        LNTPLPI_RD.TableName = "LNTPLPI";
        LNTPLPI_RD.upd = true;
        IBS.READ(SCCGWA, LNRPLPI, LNTPLPI_RD);
        WS_FOUND_FLG = ' ';
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FOUND_FLG = 'Y';
        }
    }
    public void T00_REWRITE_LNTPLPI() throws IOException,SQLException,Exception {
        LNTPLPI_RD = new DBParm();
        LNTPLPI_RD.TableName = "LNTPLPI";
        IBS.REWRITE(SCCGWA, LNRPLPI, LNTPLPI_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
