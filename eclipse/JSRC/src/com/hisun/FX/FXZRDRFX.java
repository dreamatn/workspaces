package com.hisun.FX;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class FXZRDRFX {
    String JIBS_tmp_str[] = new String[10];
    DBParm FXTDIRFX_RD;
    brParm FXTDIRFX_BR = new brParm();
    boolean pgmRtn = false;
    FXZRDRFX_WS0 WS0 = new FXZRDRFX_WS0();
    FXZRDRFX_WS_BROW_DATA WS_BROW_DATA = new FXZRDRFX_WS_BROW_DATA();
    FXZRDRFX_WS_VARIABLES WS_VARIABLES = new FXZRDRFX_WS_VARIABLES();
    FXCMSG_ERROR_MSG ERROR_MSG = new FXCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    FXRDIRFX FXRDIRFX = new FXRDIRFX();
    BPCPRMR BPCPRMR = new BPCPRMR();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SC_AREA;
    FXCRDRFX FXCRDRFX;
    FXRDIRFX FXRDIRFX;
    public void MP(SCCGWA SCCGWA, FXCRDRFX FXCRDRFX) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.FXCRDRFX = FXCRDRFX;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "FXZRDRFX return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        FXRDIRFX = (FXRDIRFX) FXCRDRFX.REC_PTR;
        FXCRDRFX.RETURN_INFO = 'F';
        IBS.init(SCCGWA, FXRDIRFX);
        IBS.init(SCCGWA, WS_BROW_DATA);
        WS_VARIABLES.BROW_CONT_9 = 0;
        CEP.TRC(SCCGWA, FXCRDRFX.REC_LEN);
        FXCRDRFX.REC_LEN = 2776;
        CEP.TRC(SCCGWA, FXCRDRFX.REC_LEN);
        CEP.TRC(SCCGWA, "----001");
        CEP.TRC(SCCGWA, FXRDIRFX);
        CEP.TRC(SCCGWA, "----002");
        IBS.CLONE(SCCGWA, FXRDIRFX, FXRDIRFX);
        CEP.TRC(SCCGWA, "----003");
        FXCRDRFX.RC.RC_MMO = "FX";
        FXCRDRFX.RC.RC_CODE = 0;
        CEP.TRC(SCCGWA, "----004");
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "00002222");
        if (FXCRDRFX.FUNC == 'I') {
            B010_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (FXCRDRFX.FUNC == 'A') {
            B020_ADD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (FXCRDRFX.FUNC == 'R') {
            B030_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (FXCRDRFX.FUNC == 'U') {
            B040_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (FXCRDRFX.FUNC == 'D') {
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (FXCRDRFX.FUNC == 'B') {
            B060_BROWSE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + FXCRDRFX.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        IBS.CLONE(SCCGWA, FXRDIRFX, FXRDIRFX);
    }
    public void B010_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_REC_PROC();
        if (pgmRtn) return;
    }
    public void B020_ADD_RECORD_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, FXRDIRFX.KEY.UPD_BR);
        CEP.TRC(SCCGWA, FXRDIRFX.KEY.TRN_DT);
        CEP.TRC(SCCGWA, FXRDIRFX.KEY.SEQ);
        CEP.TRC(SCCGWA, FXRDIRFX.KEY.JRN_NO);
        T000_WRITE_REC_PROC();
        if (pgmRtn) return;
    }
    public void B030_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_UPDATE_PROC();
        if (pgmRtn) return;
    }
    public void B040_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_REC_PROC();
        if (pgmRtn) return;
    }
    public void B050_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_REC_PROC();
        if (pgmRtn) return;
    }
    public void B060_10_START_BROWSE_PROC() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, FXCRDRFX.BROW_DATA);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_BROW_DATA);
        CEP.TRC(SCCGWA, WS_BROW_DATA.BROW_UPD_BR);
        CEP.TRC(SCCGWA, "DEVHZ001");
        CEP.TRC(SCCGWA, FXRDIRFX.KEY.UPD_BR);
        CEP.TRC(SCCGWA, WS_BROW_DATA.BROW_TRA_AC);
        CEP.TRC(SCCGWA, FXRDIRFX.TRA_AC);
        B060_30_BROWSE_ALL_PROC();
        if (pgmRtn) return;
    }
    public void B060_BROWSE_RECORD_PROC() throws IOException,SQLException,Exception {
        if (FXCRDRFX.OPT == 'S') {
            B060_10_START_BROWSE_PROC();
            if (pgmRtn) return;
        } else if (FXCRDRFX.OPT == 'R') {
            B060_30_READ_NEXT_PROC();
            if (pgmRtn) return;
        } else if (FXCRDRFX.OPT == 'E') {
            B060_50_END_BROWSE_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID OPT(" + FXCRDRFX.OPT + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B060_30_BROWSE_ALL_PROC() throws IOException,SQLException,Exception {
        WS_VARIABLES.WS_BROW_CONT.BR = '1';
        if (WS_BROW_DATA.BROW_REG_CD.trim().length() == 0) {
            WS_VARIABLES.WS_BROW_CONT.REGCD = '0';
        } else {
            WS_VARIABLES.WS_BROW_CONT.REGCD = '1';
        }
        if (WS_BROW_DATA.BROW_PROD_CD.trim().length() == 0) {
            WS_VARIABLES.WS_BROW_CONT.PROD = '0';
        } else {
            WS_VARIABLES.WS_BROW_CONT.PROD = '1';
        }
        if (WS_BROW_DATA.BROW_CI_NO.trim().length() == 0) {
            WS_VARIABLES.WS_BROW_CONT.CINO = '0';
        } else {
            WS_VARIABLES.WS_BROW_CONT.CINO = '1';
        }
        if (WS_BROW_DATA.BROW_BUY_CCY.trim().length() == 0 
            && WS_BROW_DATA.BROW_BUY_AMT == 0) {
            WS_VARIABLES.WS_BROW_CONT.BUY = '0';
        }
        if (WS_BROW_DATA.BROW_BUY_CCY.trim().length() > 0 
            && WS_BROW_DATA.BROW_BUY_AMT != 0) {
            WS_VARIABLES.WS_BROW_CONT.BUY = '1';
        }
        if (WS_BROW_DATA.BROW_SELL_CCY.trim().length() == 0 
            && WS_BROW_DATA.BROW_SELL_AMT == 0) {
            WS_VARIABLES.WS_BROW_CONT.SELL = '0';
        }
        if (WS_BROW_DATA.BROW_SELL_CCY.trim().length() > 0 
            && WS_BROW_DATA.BROW_SELL_AMT != 0) {
            WS_VARIABLES.WS_BROW_CONT.SELL = '1';
        }
        if (WS_BROW_DATA.BROW_STA_DATE == 0 
            && WS_BROW_DATA.BROW_END_DATE == 0) {
            WS_VARIABLES.WS_BROW_CONT.DT = '0';
        }
        if (WS_BROW_DATA.BROW_STA_DATE != 0 
            && WS_BROW_DATA.BROW_END_DATE != 0) {
            WS_VARIABLES.WS_BROW_CONT.DT = '1';
        }
        if (WS_BROW_DATA.BROW_STS == ' ') {
            WS_VARIABLES.WS_BROW_CONT.STS = '0';
        } else {
            WS_VARIABLES.WS_BROW_CONT.STS = '1';
        }
        CEP.TRC(SCCGWA, WS_BROW_DATA.BROW_TRA_AC);
        if (WS_BROW_DATA.BROW_TRA_AC.trim().length() == 0 
            || WS_BROW_DATA.BROW_TRA_AC.equalsIgnoreCase("0")) {
            WS_VARIABLES.WS_BROW_CONT.TRA_AC = '0';
        } else {
            WS_VARIABLES.WS_BROW_CONT.TRA_AC = '1';
        }
        T000_STARTBR_PROC();
        if (pgmRtn) return;
    }
    public void B060_30_READ_NEXT_PROC() throws IOException,SQLException,Exception {
        T000_READNEXT_PROC();
        if (pgmRtn) return;
    }
    public void B060_50_END_BROWSE_PROC() throws IOException,SQLException,Exception {
        T000_ENDBR_PROC();
        if (pgmRtn) return;
    }
    public void T000_READ_REC_PROC() throws IOException,SQLException,Exception {
        FXTDIRFX_RD = new DBParm();
        FXTDIRFX_RD.TableName = "FXTDIRFX";
        FXTDIRFX_RD.where = "TRN_DT = :FXRDIRFX.KEY.TRN_DT "
            + "AND JRN_NO = :FXRDIRFX.KEY.JRN_NO";
        IBS.READ(SCCGWA, FXRDIRFX, this, FXTDIRFX_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.FX_REC_NOTFND, FXCRDRFX.RC);
            FXCRDRFX.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_REC_PROC() throws IOException,SQLException,Exception {
        FXTDIRFX_RD = new DBParm();
        FXTDIRFX_RD.TableName = "FXTDIRFX";
        IBS.WRITE(SCCGWA, FXRDIRFX, FXTDIRFX_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.FX_REC_EXIST, FXCRDRFX.RC);
            FXCRDRFX.RETURN_INFO = 'D';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_UPDATE_PROC() throws IOException,SQLException,Exception {
        FXTDIRFX_RD = new DBParm();
        FXTDIRFX_RD.TableName = "FXTDIRFX";
        FXTDIRFX_RD.where = "TRN_DT = :FXRDIRFX.KEY.TRN_DT "
            + "AND JRN_NO = :FXRDIRFX.KEY.JRN_NO";
        FXTDIRFX_RD.upd = true;
        IBS.READ(SCCGWA, FXRDIRFX, this, FXTDIRFX_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.FX_REC_NOTFND, FXCRDRFX.RC);
            FXCRDRFX.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_REC_PROC() throws IOException,SQLException,Exception {
        FXTDIRFX_RD = new DBParm();
        FXTDIRFX_RD.TableName = "FXTDIRFX";
        IBS.REWRITE(SCCGWA, FXRDIRFX, FXTDIRFX_RD);
    }
    public void T000_DELETE_REC_PROC() throws IOException,SQLException,Exception {
        FXTDIRFX_RD = new DBParm();
        FXTDIRFX_RD.TableName = "FXTDIRFX";
        IBS.DELETE(SCCGWA, FXRDIRFX, FXTDIRFX_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.FX_REC_NOTFND, FXCRDRFX.RC);
            FXCRDRFX.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_PROC() throws IOException,SQLException,Exception {
        if (WS_VARIABLES.WS_BROW_CONT.REGCD == '1') {
            FXTDIRFX_BR.rp = new DBParm();
            FXTDIRFX_BR.rp.TableName = "FXTDIRFX";
            FXTDIRFX_BR.rp.where = "UPD_BR = :WS_BROW_DATA.BROW_UPD_BR "
                + "AND BU_TRCHNL < > 'CASH' "
                + "AND ES_REG_CD = :WS_BROW_DATA.BROW_REG_CD";
            FXTDIRFX_BR.rp.order = "TRN_DT, SEQ";
            IBS.STARTBR(SCCGWA, FXRDIRFX, this, FXTDIRFX_BR);
        } else if (WS_VARIABLES.BROW_CONT_9 == 101000100) {
            FXTDIRFX_BR.rp = new DBParm();
            FXTDIRFX_BR.rp.TableName = "FXTDIRFX";
            FXTDIRFX_BR.rp.where = "UPD_BR = :WS_BROW_DATA.BROW_UPD_BR "
                + "AND PROD_CD = :WS_BROW_DATA.BROW_PROD_CD "
                + "AND BU_TRCHNL < > 'CASH' "
                + "AND TRN_DT >= :WS_BROW_DATA.BROW_STA_DATE "
                + "AND TRN_DT <= :WS_BROW_DATA.BROW_END_DATE";
            FXTDIRFX_BR.rp.order = "TRN_DT, SEQ";
            IBS.STARTBR(SCCGWA, FXRDIRFX, this, FXTDIRFX_BR);
        } else if (WS_VARIABLES.BROW_CONT_9 == 101000110) {
            FXTDIRFX_BR.rp = new DBParm();
            FXTDIRFX_BR.rp.TableName = "FXTDIRFX";
            FXTDIRFX_BR.rp.where = "UPD_BR = :WS_BROW_DATA.BROW_UPD_BR "
                + "AND PROD_CD = :WS_BROW_DATA.BROW_PROD_CD "
                + "AND BU_TRCHNL < > 'CASH' "
                + "AND STATUS = :WS_BROW_DATA.BROW_STS "
                + "AND TRN_DT >= :WS_BROW_DATA.BROW_STA_DATE "
                + "AND TRN_DT <= :WS_BROW_DATA.BROW_END_DATE";
            FXTDIRFX_BR.rp.order = "TRN_DT, SEQ";
            IBS.STARTBR(SCCGWA, FXRDIRFX, this, FXTDIRFX_BR);
        } else if (WS_VARIABLES.BROW_CONT_9 == 101100100) {
            FXTDIRFX_BR.rp = new DBParm();
            FXTDIRFX_BR.rp.TableName = "FXTDIRFX";
            FXTDIRFX_BR.rp.where = "UPD_BR = :WS_BROW_DATA.BROW_UPD_BR "
                + "AND PROD_CD = :WS_BROW_DATA.BROW_PROD_CD "
                + "AND CI_NO = :WS_BROW_DATA.BROW_CI_NO "
                + "AND BU_TRCHNL < > 'CASH' "
                + "AND TRN_DT >= :WS_BROW_DATA.BROW_STA_DATE "
                + "AND TRN_DT <= :WS_BROW_DATA.BROW_END_DATE";
            FXTDIRFX_BR.rp.order = "TRN_DT, SEQ";
            IBS.STARTBR(SCCGWA, FXRDIRFX, this, FXTDIRFX_BR);
        } else if (WS_VARIABLES.BROW_CONT_9 == 100000001) {
            FXTDIRFX_BR.rp = new DBParm();
            FXTDIRFX_BR.rp.TableName = "FXTDIRFX";
            FXTDIRFX_BR.rp.where = "UPD_BR = :WS_BROW_DATA.BROW_UPD_BR "
                + "AND BU_TRCHNL < > 'CASH' "
                + "AND TRA_AC = :WS_BROW_DATA.BROW_TRA_AC";
            FXTDIRFX_BR.rp.order = "TRN_DT, SEQ";
            IBS.STARTBR(SCCGWA, FXRDIRFX, this, FXTDIRFX_BR);
        } else if (WS_VARIABLES.BROW_CONT_9 == 100000010) {
            FXTDIRFX_BR.rp = new DBParm();
            FXTDIRFX_BR.rp.TableName = "FXTDIRFX";
            FXTDIRFX_BR.rp.where = "UPD_BR = :WS_BROW_DATA.BROW_UPD_BR "
                + "AND BU_TRCHNL < > 'CASH' "
                + "AND STATUS = :WS_BROW_DATA.BROW_STS";
            FXTDIRFX_BR.rp.order = "TRN_DT, SEQ";
            IBS.STARTBR(SCCGWA, FXRDIRFX, this, FXTDIRFX_BR);
        } else {
            CEP.TRC(SCCGWA, "OTHER");
            FXTDIRFX_BR.rp = new DBParm();
            FXTDIRFX_BR.rp.TableName = "FXTDIRFX";
            FXTDIRFX_BR.rp.where = "( :FXRDIRFX.KEY.UPD_BR = 0 "
                + "OR UPD_BR = :FXRDIRFX.KEY.UPD_BR ) "
                + "AND BU_TRCHNL < > 'CASH' "
                + "AND ( :FXRDIRFX.PROD_CD = ' ' "
                + "OR PROD_CD = :FXRDIRFX.PROD_CD ) "
                + "AND ( :FXRDIRFX.ES_REG_CD = ' ' "
                + "OR ES_REG_CD = :FXRDIRFX.ES_REG_CD ) "
                + "AND ( :FXRDIRFX.CI_NO = ' ' "
                + "OR CI_NO = :FXRDIRFX.CI_NO ) "
                + "AND ( :FXRDIRFX.TRA_AC = ' ' "
                + "OR TRA_AC = :FXRDIRFX.TRA_AC ) "
                + "AND ( :FXRDIRFX.BUY_CCY = ' ' "
                + "OR BUY_CCY = :FXRDIRFX.BUY_CCY ) "
                + "AND ( :FXRDIRFX.BUY_AMT = 0 "
                + "OR BUY_AMT = :FXRDIRFX.BUY_AMT ) "
                + "AND ( :FXRDIRFX.SELL_CCY = ' ' "
                + "OR SELL_CCY = :FXRDIRFX.SELL_CCY ) "
                + "AND ( :FXRDIRFX.SELL_AMT = 0 "
                + "OR SELL_AMT = :FXRDIRFX.SELL_AMT ) "
                + "AND ( :FXRDIRFX.VALUE_DT = 0 "
                + "OR VALUE_DT >= :FXRDIRFX.VALUE_DT ) "
                + "AND ( :FXRDIRFX.O_END_DT = 0 "
                + "OR O_END_DT <= :FXRDIRFX.O_END_DT ) "
                + "AND ( :FXRDIRFX.STATUS = ' ' "
                + "OR STATUS = :FXRDIRFX.STATUS ) "
                + "AND ( :FXRDIRFX.ID_TYP = ' ' "
                + "OR ID_TYP = :FXRDIRFX.ID_TYP ) "
                + "AND ( :FXRDIRFX.ID_NO = ' ' "
                + "OR ID_NO = :FXRDIRFX.ID_NO )";
            FXTDIRFX_BR.rp.order = "TRN_DT, SEQ";
            IBS.STARTBR(SCCGWA, FXRDIRFX, this, FXTDIRFX_BR);
    }
    public void T000_READNEXT_PROC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, FXRDIRFX, this, FXTDIRFX_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            FXCRDRFX.RETURN_INFO = 'E';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_PROC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, FXTDIRFX_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
