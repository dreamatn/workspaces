package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRRTFW {
    DBParm BPTRTFW_RD;
    brParm BPTRTFW_BR = new brParm();
    boolean pgmRtn = false;
    int WS_STARTD = 0;
    int WS_ENDD = 0;
    String TBL_BPTRTFW = "BPTRTFW";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    BPRRTFW BPRRTFW = new BPRRTFW();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCRRTFW BPCRRTFW;
    BPRRTFW BPRRTFWL;
    public void MP(SCCGWA SCCGWA, BPCRRTFW BPCRRTFW) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRRTFW = BPCRRTFW;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "END-OF-A000-INIT");
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRRTFW return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, BPCRRTFW.RC);
        BPRRTFWL = (BPRRTFW) BPCRRTFW.INFO.POINTER;
        IBS.init(SCCGWA, BPRRTFW);
        CEP.TRC(SCCGWA, BPCRRTFW.INFO.LEN);
        if ((BPCRRTFW.INFO.LEN > 0)) {
            IBS.CLONE(SCCGWA, BPRRTFWL, BPRRTFW);
        }
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRRTFW.INFO.FUNC == 'C') {
            B010_CREATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRRTFW.INFO.FUNC == 'R') {
            B020_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRRTFW.INFO.FUNC == 'U') {
            B030_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRRTFW.INFO.FUNC == 'Q') {
            B040_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRRTFW.INFO.FUNC == 'D') {
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRRTFW.INFO.FUNC == 'B') {
            B060_BROWSE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, BPCRRTFW.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPRRTFW, BPRRTFWL);
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BPTRTFW();
        if (pgmRtn) return;
    }
    public void B020_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTRTFW_UPD();
        if (pgmRtn) return;
    }
    public void B030_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTRTFW();
        if (pgmRtn) return;
    }
    public void B040_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTRTFW();
        if (pgmRtn) return;
    }
    public void B050_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_BPTRTFW();
        if (pgmRtn) return;
    }
    public void B060_BROWSE_RECORD_PROC() throws IOException,SQLException,Exception {
        WS_STARTD = BPCRRTFW.DATE.STARTD;
        WS_ENDD = BPCRRTFW.DATE.ENDD;
        if (BPCRRTFW.INFO.OPT == '1') {
            T000_STARTBR_BPTRTFW_1();
            if (pgmRtn) return;
        } else if (BPCRRTFW.INFO.OPT == '2') {
            T000_STARTBR_BPTRTFW_2();
            if (pgmRtn) return;
        } else if (BPCRRTFW.INFO.OPT == '3') {
            T000_STARTBR_BPTRTFW_3();
            if (pgmRtn) return;
        } else if (BPCRRTFW.INFO.OPT == '4') {
            T000_STARTBR_BPTRTFW_4();
            if (pgmRtn) return;
        } else if (BPCRRTFW.INFO.OPT == '5') {
            T000_STARTBR_BPTRTFW_5();
            if (pgmRtn) return;
        } else if (BPCRRTFW.INFO.OPT == '6') {
            T000_STARTBR_BPTRTFW_6();
            if (pgmRtn) return;
        } else if (BPCRRTFW.INFO.OPT == '7') {
            T000_STARTBR_BPTRTFW_7();
            if (pgmRtn) return;
        } else if (BPCRRTFW.INFO.OPT == '8') {
            T000_STARTBR_BPTRTFW_8();
            if (pgmRtn) return;
        } else if (BPCRRTFW.INFO.OPT == '9') {
            T000_STARTBR_BPTRTFW_9();
            if (pgmRtn) return;
        } else if (BPCRRTFW.INFO.OPT == 'A') {
            T000_STARTBR_BPTRTFW_10();
            if (pgmRtn) return;
        } else if (BPCRRTFW.INFO.OPT == 'B') {
            T000_STARTBR_BPTRTFW_11();
            if (pgmRtn) return;
        } else {
        }
        if (BPCRRTFW.INFO.OPT == 'N') {
            T000_READNEXT_BPTRTFW();
            if (pgmRtn) return;
        }
        if (BPCRRTFW.INFO.OPT == 'E') {
            T000_ENDBR_BPTRTFW();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BPTRTFW() throws IOException,SQLException,Exception {
        BPTRTFW_RD = new DBParm();
        BPTRTFW_RD.TableName = "BPTRTFW";
        BPTRTFW_RD.errhdl = true;
        IBS.READ(SCCGWA, BPRRTFW, BPTRTFW_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRRTFW.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRRTFW.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_BPTRTFW;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_BPTRTFW() throws IOException,SQLException,Exception {
        BPTRTFW_RD = new DBParm();
        BPTRTFW_RD.TableName = "BPTRTFW";
        BPTRTFW_RD.errhdl = true;
        IBS.WRITE(SCCGWA, BPRRTFW, BPTRTFW_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRRTFW.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCRRTFW.RETURN_INFO = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_BPTRTFW;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BPTRTFW_UPD() throws IOException,SQLException,Exception {
        BPTRTFW_RD = new DBParm();
        BPTRTFW_RD.TableName = "BPTRTFW";
        BPTRTFW_RD.upd = true;
        IBS.READ(SCCGWA, BPRRTFW, BPTRTFW_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRRTFW.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRRTFW.RETURN_INFO = 'N';
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INT_RECORD_NOT_EXIST, BPCRRTFW.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_BPTRTFW;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_BPTRTFW() throws IOException,SQLException,Exception {
        BPTRTFW_RD = new DBParm();
        BPTRTFW_RD.TableName = "BPTRTFW";
        IBS.REWRITE(SCCGWA, BPRRTFW, BPTRTFW_RD);
    }
    public void T000_DELETE_BPTRTFW() throws IOException,SQLException,Exception {
        BPTRTFW_RD = new DBParm();
        BPTRTFW_RD.TableName = "BPTRTFW";
        IBS.DELETE(SCCGWA, BPRRTFW, BPTRTFW_RD);
    }
    public void T000_STARTBR_BPTRTFW_1() throws IOException,SQLException,Exception {
        BPTRTFW_BR.rp = new DBParm();
        BPTRTFW_BR.rp.TableName = "BPTRTFW";
        BPTRTFW_BR.rp.where = "VAL_DT <= :BPRRTFW.KEY.VAL_DT";
        BPTRTFW_BR.rp.upd = true;
        BPTRTFW_BR.rp.order = "BR,CCY,BASE_TYP,TENOR,VAL_DT";
        IBS.STARTBR(SCCGWA, BPRRTFW, this, BPTRTFW_BR);
    }
    public void T000_STARTBR_BPTRTFW_2() throws IOException,SQLException,Exception {
        BPTRTFW_BR.rp = new DBParm();
        BPTRTFW_BR.rp.TableName = "BPTRTFW";
        BPTRTFW_BR.rp.where = "BR = :BPRRTFW.KEY.BR "
            + "AND BASE_TYP = :BPRRTFW.KEY.BASE_TYP "
            + "AND TENOR = :BPRRTFW.KEY.TENOR";
        IBS.STARTBR(SCCGWA, BPRRTFW, this, BPTRTFW_BR);
    }
    public void T000_STARTBR_BPTRTFW_3() throws IOException,SQLException,Exception {
        BPTRTFW_BR.rp = new DBParm();
        BPTRTFW_BR.rp.TableName = "BPTRTFW";
        BPTRTFW_BR.rp.where = "BR = :BPRRTFW.KEY.BR "
            + "AND BASE_TYP = :BPRRTFW.KEY.BASE_TYP "
            + "AND TENOR = :BPRRTFW.KEY.TENOR "
            + "AND CCY = :BPRRTFW.KEY.CCY";
        IBS.STARTBR(SCCGWA, BPRRTFW, this, BPTRTFW_BR);
    }
    public void T000_STARTBR_BPTRTFW_4() throws IOException,SQLException,Exception {
        BPTRTFW_BR.rp = new DBParm();
        BPTRTFW_BR.rp.TableName = "BPTRTFW";
        BPTRTFW_BR.rp.where = "BR = :BPRRTFW.KEY.BR "
            + "AND BASE_TYP = :BPRRTFW.KEY.BASE_TYP "
            + "AND CCY = :BPRRTFW.KEY.CCY";
        IBS.STARTBR(SCCGWA, BPRRTFW, this, BPTRTFW_BR);
    }
    public void T000_STARTBR_BPTRTFW_5() throws IOException,SQLException,Exception {
        BPTRTFW_BR.rp = new DBParm();
        BPTRTFW_BR.rp.TableName = "BPTRTFW";
        BPTRTFW_BR.rp.where = "BR = :BPRRTFW.KEY.BR "
            + "AND BASE_TYP = :BPRRTFW.KEY.BASE_TYP";
        IBS.STARTBR(SCCGWA, BPRRTFW, this, BPTRTFW_BR);
    }
    public void T000_STARTBR_BPTRTFW_6() throws IOException,SQLException,Exception {
        BPTRTFW_RD = new DBParm();
        BPTRTFW_RD.TableName = "BPTRTFW";
        BPTRTFW_RD.where = "BR = :BPRRTFW.KEY.BR "
            + "AND BASE_TYP = :BPRRTFW.KEY.BASE_TYP "
            + "AND TENOR = :BPRRTFW.KEY.TENOR "
            + "AND CCY = :BPRRTFW.KEY.CCY "
            + "AND VAL_DT <= :BPRRTFW.KEY.VAL_DT";
        BPTRTFW_RD.fst = true;
        BPTRTFW_RD.order = "VAL_DT DESC";
        IBS.READ(SCCGWA, BPRRTFW, this, BPTRTFW_RD);
    }
    public void T000_STARTBR_BPTRTFW_7() throws IOException,SQLException,Exception {
        BPTRTFW_BR.rp = new DBParm();
        BPTRTFW_BR.rp.TableName = "BPTRTFW";
        BPTRTFW_BR.rp.where = "BR = :BPRRTFW.KEY.BR "
            + "AND TENOR = :BPRRTFW.KEY.TENOR";
        IBS.STARTBR(SCCGWA, BPRRTFW, this, BPTRTFW_BR);
    }
    public void T000_STARTBR_BPTRTFW_8() throws IOException,SQLException,Exception {
        BPTRTFW_BR.rp = new DBParm();
        BPTRTFW_BR.rp.TableName = "BPTRTFW";
        BPTRTFW_BR.rp.where = "BR = :BPRRTFW.KEY.BR "
            + "AND CCY = :BPRRTFW.KEY.CCY";
        IBS.STARTBR(SCCGWA, BPRRTFW, this, BPTRTFW_BR);
    }
    public void T000_STARTBR_BPTRTFW_9() throws IOException,SQLException,Exception {
        BPTRTFW_BR.rp = new DBParm();
        BPTRTFW_BR.rp.TableName = "BPTRTFW";
        BPTRTFW_BR.rp.where = "BR = :BPRRTFW.KEY.BR "
            + "AND CCY = :BPRRTFW.KEY.CCY "
            + "AND TENOR = :BPRRTFW.KEY.TENOR";
        IBS.STARTBR(SCCGWA, BPRRTFW, this, BPTRTFW_BR);
    }
    public void T000_STARTBR_BPTRTFW_10() throws IOException,SQLException,Exception {
        BPTRTFW_BR.rp = new DBParm();
        BPTRTFW_BR.rp.TableName = "BPTRTFW";
        BPTRTFW_BR.rp.where = "BR = :BPRRTFW.KEY.BR "
            + "AND BASE_TYP LIKE :BPRRTFW.KEY.BASE_TYP "
            + "AND CCY LIKE :BPRRTFW.KEY.CCY "
            + "AND VAL_DT >= :WS_STARTD "
            + "AND VAL_DT <= :WS_ENDD";
        BPTRTFW_BR.rp.order = "VAL_DT DESC";
        IBS.STARTBR(SCCGWA, BPRRTFW, this, BPTRTFW_BR);
    }
    public void T000_STARTBR_BPTRTFW_11() throws IOException,SQLException,Exception {
        BPTRTFW_BR.rp = new DBParm();
        BPTRTFW_BR.rp.TableName = "BPTRTFW";
        BPTRTFW_BR.rp.where = "BR = :BPRRTFW.KEY.BR "
            + "AND BASE_TYP LIKE :BPRRTFW.KEY.BASE_TYP "
            + "AND TENOR = :BPRRTFW.KEY.TENOR "
            + "AND CCY LIKE :BPRRTFW.KEY.CCY "
            + "AND VAL_DT >= :WS_STARTD "
            + "AND VAL_DT <= :WS_ENDD";
        BPTRTFW_BR.rp.order = "VAL_DT DESC";
        IBS.STARTBR(SCCGWA, BPRRTFW, this, BPTRTFW_BR);
    }
    public void T000_READNEXT_BPTRTFW() throws IOException,SQLException,Exception {
        BPTRTFW_BR.rp.errhdl = true;
        IBS.READNEXT(SCCGWA, BPRRTFW, this, BPTRTFW_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRRTFW.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRRTFW.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_BPTRTFW;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_BPTRTFW() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTRTFW_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRRTFW.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCRRTFW = ");
            CEP.TRC(SCCGWA, BPCRRTFW);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
