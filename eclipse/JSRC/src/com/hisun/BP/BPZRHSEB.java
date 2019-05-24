package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRHSEB {
    brParm BPTHSEQ_BR = new brParm();
    boolean pgmRtn = false;
    String TBL_BPTHSEQ = "BPTHSEQ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    BPRHSEQ BPRHSEQ = new BPRHSEQ();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCRHSEB BPCRHSEB;
    BPRHSEQ BPRHSEQ1;
    public void MP(SCCGWA SCCGWA, BPCRHSEB BPCRHSEB) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRHSEB = BPCRHSEB;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRHSEB return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, BPCRHSEB.RC);
        BPRHSEQ1 = (BPRHSEQ) BPCRHSEB.INFO.POINTER;
        IBS.init(SCCGWA, BPRHSEQ);
        IBS.CLONE(SCCGWA, BPRHSEQ1, BPRHSEQ);
        CEP.TRC(SCCGWA, BPRHSEQ.KEY.TYPE);
        CEP.TRC(SCCGWA, BPRHSEQ.KEY.CODE);
        CEP.TRC(SCCGWA, BPRHSEQ.KEY.SEQ_NO);
        CEP.TRC(SCCGWA, BPRHSEQ.CI_TYPE);
        CEP.TRC(SCCGWA, BPRHSEQ.CI_NO);
        CEP.TRC(SCCGWA, BPRHSEQ.AC_NO);
        CEP.TRC(SCCGWA, BPRHSEQ.CT_NO);
        CEP.TRC(SCCGWA, BPRHSEQ.CNTR_NO);
        CEP.TRC(SCCGWA, BPRHSEQ.USED_FLAG);
        CEP.TRC(SCCGWA, BPRHSEQ.USED_DATE);
        CEP.TRC(SCCGWA, BPRHSEQ.CI_NAME);
        CEP.TRC(SCCGWA, BPRHSEQ.AC_OFFICER);
        CEP.TRC(SCCGWA, BPRHSEQ.AC_DIGIT);
        CEP.TRC(SCCGWA, BPRHSEQ.REMARK);
        CEP.TRC(SCCGWA, BPRHSEQ);
        CEP.TRC(SCCGWA, BPCRHSEB);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRHSEB.INFO.FUNC == 'S') {
            B010_STARTBR_PROC();
            if (pgmRtn) return;
        } else if (BPCRHSEB.INFO.FUNC == 'R') {
            B020_READNEXT_PROC();
            if (pgmRtn) return;
        } else if (BPCRHSEB.INFO.FUNC == 'E') {
            B030_ENDBR_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, BPCRHSEB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPRHSEQ, BPRHSEQ1);
    }
    public void B010_STARTBR_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCRHSEB.INFO.OPT);
        if (BPCRHSEB.INFO.OPT == '1') {
            T000_STARTBR_BPTHSEQ_01();
            if (pgmRtn) return;
        } else if (BPCRHSEB.INFO.OPT == '2') {
            T000_STARTBR_BPTHSEQ_02();
            if (pgmRtn) return;
        } else if (BPCRHSEB.INFO.OPT == '3') {
            T000_STARTBR_BPTHSEQ_03();
            if (pgmRtn) return;
        } else if (BPCRHSEB.INFO.OPT == '4') {
            T000_STARTBR_BPTHSEQ_04();
            if (pgmRtn) return;
        } else if (BPCRHSEB.INFO.OPT == '5') {
            T000_STARTBR_BPTHSEQ_05();
            if (pgmRtn) return;
        } else if (BPCRHSEB.INFO.OPT == '6') {
            T000_STARTBR_BPTHSEQ_06();
            if (pgmRtn) return;
        } else if (BPCRHSEB.INFO.OPT == '7') {
            T000_STARTBR_BPTHSEQ_07();
            if (pgmRtn) return;
        } else if (BPCRHSEB.INFO.OPT == '8') {
            T000_STARTBR_BPTHSEQ_08();
            if (pgmRtn) return;
        } else if (BPCRHSEB.INFO.OPT == '9') {
            T000_STARTBR_BPTHSEQ_09();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, BPCRHSEB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_READNEXT_PROC() throws IOException,SQLException,Exception {
        T000_READNEXT_BPTHSEQ();
        if (pgmRtn) return;
    }
    public void B030_ENDBR_PROC() throws IOException,SQLException,Exception {
        T000_ENDBR_BPTHSEQ();
        if (pgmRtn) return;
    }
    public void T000_STARTBR_BPTHSEQ_01() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "START BROWER 01");
        CEP.TRC(SCCGWA, BPRHSEQ.KEY.TYPE);
        CEP.TRC(SCCGWA, BPRHSEQ.KEY.CODE);
        CEP.TRC(SCCGWA, BPRHSEQ.AC_OFFICER);
        CEP.TRC(SCCGWA, BPRHSEQ.USED_FLAG);
        BPTHSEQ_BR.rp = new DBParm();
        BPTHSEQ_BR.rp.TableName = "BPTHSEQ";
        BPTHSEQ_BR.rp.where = "TYPE = :BPRHSEQ.KEY.TYPE";
        IBS.STARTBR(SCCGWA, BPRHSEQ, this, BPTHSEQ_BR);
    }
    public void T000_STARTBR_BPTHSEQ_02() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "START BROWER 02");
        CEP.TRC(SCCGWA, BPRHSEQ.KEY.TYPE);
        CEP.TRC(SCCGWA, BPRHSEQ.KEY.CODE);
        CEP.TRC(SCCGWA, BPRHSEQ.AC_OFFICER);
        CEP.TRC(SCCGWA, BPRHSEQ.USED_FLAG);
        BPTHSEQ_BR.rp = new DBParm();
        BPTHSEQ_BR.rp.TableName = "BPTHSEQ";
        BPTHSEQ_BR.rp.where = "TYPE = :BPRHSEQ.KEY.TYPE "
            + "AND USED_FLAG = :BPRHSEQ.USED_FLAG";
        IBS.STARTBR(SCCGWA, BPRHSEQ, this, BPTHSEQ_BR);
    }
    public void T000_STARTBR_BPTHSEQ_03() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "START BROWER 03");
        BPTHSEQ_BR.rp = new DBParm();
        BPTHSEQ_BR.rp.TableName = "BPTHSEQ";
        BPTHSEQ_BR.rp.where = "TYPE = :BPRHSEQ.KEY.TYPE "
            + "AND CODE LIKE :BPRHSEQ.KEY.CODE";
        IBS.STARTBR(SCCGWA, BPRHSEQ, this, BPTHSEQ_BR);
    }
    public void T000_STARTBR_BPTHSEQ_04() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "START BROWER 04");
        BPTHSEQ_BR.rp = new DBParm();
        BPTHSEQ_BR.rp.TableName = "BPTHSEQ";
        BPTHSEQ_BR.rp.where = "TYPE = :BPRHSEQ.KEY.TYPE "
            + "AND CODE LIKE :BPRHSEQ.KEY.CODE "
            + "AND USED_FLAG = :BPRHSEQ.USED_FLAG";
        IBS.STARTBR(SCCGWA, BPRHSEQ, this, BPTHSEQ_BR);
    }
    public void T000_STARTBR_BPTHSEQ_05() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "START BROWER 05");
        BPTHSEQ_BR.rp = new DBParm();
        BPTHSEQ_BR.rp.TableName = "BPTHSEQ";
        BPTHSEQ_BR.rp.where = "TYPE = :BPRHSEQ.KEY.TYPE "
            + "AND AC_OFFICER = :BPRHSEQ.AC_OFFICER";
        IBS.STARTBR(SCCGWA, BPRHSEQ, this, BPTHSEQ_BR);
    }
    public void T000_STARTBR_BPTHSEQ_06() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "START BROWER 06");
        BPTHSEQ_BR.rp = new DBParm();
        BPTHSEQ_BR.rp.TableName = "BPTHSEQ";
        BPTHSEQ_BR.rp.where = "TYPE = :BPRHSEQ.KEY.TYPE "
            + "AND AC_OFFICER = :BPRHSEQ.AC_OFFICER "
            + "AND USED_FLAG = :BPRHSEQ.USED_FLAG";
        IBS.STARTBR(SCCGWA, BPRHSEQ, this, BPTHSEQ_BR);
    }
    public void T000_STARTBR_BPTHSEQ_07() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "START BROWER 07");
        BPTHSEQ_BR.rp = new DBParm();
        BPTHSEQ_BR.rp.TableName = "BPTHSEQ";
        BPTHSEQ_BR.rp.where = "TYPE = :BPRHSEQ.KEY.TYPE "
            + "AND CODE LIKE :BPRHSEQ.KEY.CODE "
            + "AND AC_OFFICER = :BPRHSEQ.AC_OFFICER";
        IBS.STARTBR(SCCGWA, BPRHSEQ, this, BPTHSEQ_BR);
    }
    public void T000_STARTBR_BPTHSEQ_08() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "START BROWER 08");
        BPTHSEQ_BR.rp = new DBParm();
        BPTHSEQ_BR.rp.TableName = "BPTHSEQ";
        BPTHSEQ_BR.rp.where = "TYPE = :BPRHSEQ.KEY.TYPE "
            + "AND CODE LIKE :BPRHSEQ.KEY.CODE "
            + "AND AC_OFFICER = :BPRHSEQ.AC_OFFICER "
            + "AND USED_FLAG = :BPRHSEQ.USED_FLAG";
        IBS.STARTBR(SCCGWA, BPRHSEQ, this, BPTHSEQ_BR);
    }
    public void T000_STARTBR_BPTHSEQ_09() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "START BROWER 09");
        CEP.TRC(SCCGWA, BPRHSEQ.KEY.TYPE);
        CEP.TRC(SCCGWA, BPRHSEQ.CI_NO);
        BPTHSEQ_BR.rp = new DBParm();
        BPTHSEQ_BR.rp.TableName = "BPTHSEQ";
        BPTHSEQ_BR.rp.where = "TYPE = :BPRHSEQ.KEY.TYPE "
            + "AND CI_NO = :BPRHSEQ.CI_NO";
        BPTHSEQ_BR.rp.order = "AC_NO";
        IBS.STARTBR(SCCGWA, BPRHSEQ, this, BPTHSEQ_BR);
    }
    public void T000_READNEXT_BPTHSEQ() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "NEXT BROWER");
        BPTHSEQ_BR.rp.errhdl = true;
        IBS.READNEXT(SCCGWA, BPRHSEQ, this, BPTHSEQ_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRHSEB.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRHSEB.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_BPTHSEQ;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_BPTHSEQ() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTHSEQ_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRHSEB.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCRHSEB = ");
            CEP.TRC(SCCGWA, BPCRHSEB);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
