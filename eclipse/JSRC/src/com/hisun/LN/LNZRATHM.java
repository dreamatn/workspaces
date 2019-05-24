package com.hisun.LN;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNZRATHM {
    String JIBS_tmp_str[] = new String[10];
    DBParm LNTRATH_RD;
    boolean pgmRtn = false;
    char LNZRATHM_FILLER1 = ' ';
    char WS_UPDATE_FLG = ' ';
    char WS_FOUND_FLG = ' ';
    LNRRATH LNRRATH = new LNRRATH();
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCGWA SCCGWA;
    LNCRATHM LNCRATHM;
    public void MP(SCCGWA SCCGWA, LNCRATHM LNCRATHM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCRATHM = LNCRATHM;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZRATHM return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        LNCRATHM.RC.RC_APP = "LN";
        LNCRATHM.RC.RC_RTNCODE = 0;
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        B01_CHECK();
        if (pgmRtn) return;
        if (LNCRATHM.FUNC == '0') {
            B02_FUNC_WRITE();
            if (pgmRtn) return;
        } else if (LNCRATHM.FUNC == '1') {
            B03_FUNC_DELETE();
            if (pgmRtn) return;
        } else if (LNCRATHM.FUNC == '2') {
            B04_FUNC_REWRITE();
            if (pgmRtn) return;
        } else if (LNCRATHM.FUNC == '3') {
            B05_FUNC_READ();
            if (pgmRtn) return;
        } else if (LNCRATHM.FUNC == '4') {
            B06_FUNC_READ_UPDATE();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_FUNC_CODE, LNCRATHM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B01_CHECK() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCRATHM.REC_DATA.KEY);
        if (JIBS_tmp_str[0].trim().length() == 0 
            || JIBS_tmp_str[0].equalsIgnoreCase("0")) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_KEY_MUST_INPUT, LNCRATHM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B02_FUNC_WRITE() throws IOException,SQLException,Exception {
        WS_UPDATE_FLG = ' ';
        B10_READ();
        if (pgmRtn) return;
        if (WS_FOUND_FLG == 'Y') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_RATH_EXIST, LNCRATHM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        LNCRATHM.REC_DATA.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNCRATHM.REC_DATA.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNCRATHM.REC_DATA.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNCRATHM.REC_DATA.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        R00_COMA_RECA();
        if (pgmRtn) return;
        T00_WRITE_LNTRATH();
        if (pgmRtn) return;
    }
    public void B03_FUNC_DELETE() throws IOException,SQLException,Exception {
        LNRRATH.KEY.CONTRACT_NO = LNCRATHM.REC_DATA.KEY.CONTRACT_NO;
        LNRRATH.KEY.SUB_CTA_NO = LNCRATHM.REC_DATA.KEY.SUB_CTA_NO;
        LNRRATH.KEY.RATE_TYP = LNCRATHM.REC_DATA.KEY.RATE_TYP;
        LNRRATH.KEY.RAT_CHG_DT = LNCRATHM.REC_DATA.KEY.RAT_CHG_DT;
        T00_READUPDATE_LNTRATH();
        if (pgmRtn) return;
        if (WS_FOUND_FLG == 'Y') {
            T00_DELETE_LNTRATH();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_RATH_NOTFND, LNCRATHM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B04_FUNC_REWRITE() throws IOException,SQLException,Exception {
        LNCRATHM.REC_DATA.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNCRATHM.REC_DATA.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        R00_COMA_RECA();
        if (pgmRtn) return;
        T00_REWRITE_LNTRATH();
        if (pgmRtn) return;
    }
    public void B05_FUNC_READ() throws IOException,SQLException,Exception {
        WS_UPDATE_FLG = ' ';
        B10_READ();
        if (pgmRtn) return;
        if (WS_FOUND_FLG != 'Y') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_RATH_NOTFND, LNCRATHM.RC);
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
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_RATH_NOTFND, LNCRATHM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        R00_RECA_COMA();
        if (pgmRtn) return;
    }
    public void B10_READ() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRRATH);
        LNRRATH.KEY.CONTRACT_NO = LNCRATHM.REC_DATA.KEY.CONTRACT_NO;
        LNRRATH.KEY.SUB_CTA_NO = LNCRATHM.REC_DATA.KEY.SUB_CTA_NO;
        LNRRATH.KEY.RATE_TYP = LNCRATHM.REC_DATA.KEY.RATE_TYP;
        LNRRATH.KEY.RAT_CHG_DT = LNCRATHM.REC_DATA.KEY.RAT_CHG_DT;
        if (WS_UPDATE_FLG == 'Y') {
            T00_READUPDATE_LNTRATH();
            if (pgmRtn) return;
        } else {
            T00_READ_LNTRATH();
            if (pgmRtn) return;
        }
    }
    public void R00_COMA_RECA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRRATH);
        LNRRATH.KEY.CONTRACT_NO = LNCRATHM.REC_DATA.KEY.CONTRACT_NO;
        LNRRATH.KEY.SUB_CTA_NO = LNCRATHM.REC_DATA.KEY.SUB_CTA_NO;
        LNRRATH.KEY.RATE_TYP = LNCRATHM.REC_DATA.KEY.RATE_TYP;
        LNRRATH.KEY.RAT_CHG_DT = LNCRATHM.REC_DATA.KEY.RAT_CHG_DT;
        LNRRATH.INT_RAT = LNCRATHM.REC_DATA.INT_RAT;
        LNRRATH.RATE_FROM_SEQ = LNCRATHM.REC_DATA.RATE_FROM_SEQ;
        LNRRATH.BASE_RATE = LNCRATHM.REC_DATA.BASE_RATE;
        LNRRATH.BASE_RATE_FLG = LNCRATHM.REC_DATA.BASE_RATE_FLG;
        LNRRATH.RATE_KIND = LNCRATHM.REC_DATA.RATE_KIND;
        LNRRATH.CRT_DATE = LNCRATHM.REC_DATA.CRT_DATE;
        LNRRATH.CRT_TLR = LNCRATHM.REC_DATA.CRT_TLR;
        LNRRATH.UPDTBL_DATE = LNCRATHM.REC_DATA.UPDTBL_DATE;
        LNRRATH.UPDTBL_TLR = LNCRATHM.REC_DATA.UPDTBL_TLR;
    }
    public void R00_RECA_COMA() throws IOException,SQLException,Exception {
        LNCRATHM.REC_DATA.KEY.CONTRACT_NO = LNRRATH.KEY.CONTRACT_NO;
        LNCRATHM.REC_DATA.KEY.SUB_CTA_NO = LNRRATH.KEY.SUB_CTA_NO;
        LNCRATHM.REC_DATA.KEY.RATE_TYP = LNRRATH.KEY.RATE_TYP;
        LNCRATHM.REC_DATA.KEY.RAT_CHG_DT = LNRRATH.KEY.RAT_CHG_DT;
        LNCRATHM.REC_DATA.INT_RAT = LNRRATH.INT_RAT;
        LNCRATHM.REC_DATA.RATE_FROM_SEQ = LNRRATH.RATE_FROM_SEQ;
        LNCRATHM.REC_DATA.RATE_KIND = LNRRATH.RATE_KIND;
        LNCRATHM.REC_DATA.CRT_DATE = LNRRATH.CRT_DATE;
        LNCRATHM.REC_DATA.CRT_TLR = LNRRATH.CRT_TLR;
        LNCRATHM.REC_DATA.UPDTBL_DATE = LNRRATH.UPDTBL_DATE;
        LNCRATHM.REC_DATA.UPDTBL_TLR = LNRRATH.UPDTBL_TLR;
        LNCRATHM.REC_DATA.TS = LNRRATH.TS;
    }
    public void T00_WRITE_LNTRATH() throws IOException,SQLException,Exception {
        LNTRATH_RD = new DBParm();
        LNTRATH_RD.TableName = "LNTRATH";
        IBS.WRITE(SCCGWA, LNRRATH, LNTRATH_RD);
    }
    public void T00_DELETE_LNTRATH() throws IOException,SQLException,Exception {
        LNTRATH_RD = new DBParm();
        LNTRATH_RD.TableName = "LNTRATH";
        IBS.DELETE(SCCGWA, LNRRATH, LNTRATH_RD);
    }
    public void T00_READ_LNTRATH() throws IOException,SQLException,Exception {
        LNTRATH_RD = new DBParm();
        LNTRATH_RD.TableName = "LNTRATH";
        IBS.READ(SCCGWA, LNRRATH, LNTRATH_RD);
        WS_FOUND_FLG = ' ';
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FOUND_FLG = 'Y';
        }
    }
    public void T00_READUPDATE_LNTRATH() throws IOException,SQLException,Exception {
        LNTRATH_RD = new DBParm();
        LNTRATH_RD.TableName = "LNTRATH";
        LNTRATH_RD.upd = true;
        IBS.READ(SCCGWA, LNRRATH, LNTRATH_RD);
        WS_FOUND_FLG = ' ';
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FOUND_FLG = 'Y';
        }
    }
    public void T00_REWRITE_LNTRATH() throws IOException,SQLException,Exception {
        LNTRATH_RD = new DBParm();
        LNTRATH_RD.TableName = "LNTRATH";
        IBS.REWRITE(SCCGWA, LNRRATH, LNTRATH_RD);
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
