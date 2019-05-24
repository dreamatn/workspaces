package com.hisun.TD;

import com.hisun.SC.*;
import com.hisun.SC.SCCBINF;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

import java.io.IOException;
import java.sql.SQLException;

public class TDZUCACK {
    int JIBS_tmp_int;
    brParm TDTINST_BR = new brParm();
    brParm TDTCDI_BR = new brParm();
    DBParm TDTSMST_RD;
    boolean pgmRtn = false;
    char WS_FOUND_FLG = ' ';
    char WS_INST_FLAG = ' ';
    char WS_CDI_FLAG = ' ';
    String WS_ERR_MSG = " ";
    String WS_TD_AC = " ";
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCBINF SCCBINF = new SCCBINF();
    TDRSMST TDRSMST = new TDRSMST();
    TDRINST TDRINST = new TDRINST();
    TDRCDI TDRCDI = new TDRCDI();
    SCCGWA SCCGWA;
    TDCUCACK TDCUCACK;
    public void MP(SCCGWA SCCGWA, TDCUCACK TDCUCACK) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.TDCUCACK = TDCUCACK;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "TDZUCACK return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B030_CHECK_AC_JOIN_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCUCACK.AC_NO);
        if (TDCUCACK.AC_NO.trim().length() == 0) {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        TDCUCACK.FOUND_FLG = 'N';
        WS_FOUND_FLG = 'N';
    }
    public void B030_CHECK_AC_JOIN_PROC() throws IOException,SQLException,Exception {
        R000_CHECK_TDTINST_PROC();
        if (pgmRtn) return;
        R000_CHECK_TDTINST_PROC_2();
        if (pgmRtn) return;
        R000_CHECK_TDTCDI_PROC();
        if (pgmRtn) return;
    }
    public void R000_CHECK_TDTINST_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRINST);
        TDRINST.STL_AC = TDCUCACK.AC_NO;
        T000_STARTBR_TDTINST();
        if (pgmRtn) return;
        T000_READNEXT_TDTINST();
        if (pgmRtn) return;
        while (WS_INST_FLAG != 'N' 
            && WS_FOUND_FLG != 'Y') {
            WS_TD_AC = TDRINST.KEY.ACO_AC;
            R000_CHECK_SMST_STS();
            if (pgmRtn) return;
            T000_READNEXT_TDTINST();
            if (pgmRtn) return;
        }
        T000_ENDBR_TDTINST();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CHECK TDTINST END");
        CEP.TRC(SCCGWA, WS_FOUND_FLG);
        if (WS_FOUND_FLG == 'Y') {
            TDCUCACK.ZC_FLG = 'Y';
            TDCUCACK.FOUND_FLG = 'Y';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void R000_CHECK_TDTINST_PROC_2() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRINST);
        TDRINST.STL_INT_AC = TDCUCACK.AC_NO;
        T000_STARTBR_TDTINST_2();
        if (pgmRtn) return;
        T000_READNEXT_TDTINST();
        if (pgmRtn) return;
        while (WS_INST_FLAG != 'N' 
            && WS_FOUND_FLG != 'Y') {
            WS_TD_AC = TDRINST.KEY.ACO_AC;
            R000_CHECK_SMST_STS();
            if (pgmRtn) return;
            T000_READNEXT_TDTINST();
            if (pgmRtn) return;
        }
        T000_ENDBR_TDTINST();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CHECK TDTINST END");
        CEP.TRC(SCCGWA, WS_FOUND_FLG);
        if (WS_FOUND_FLG == 'Y') {
            TDCUCACK.ZC_FLG = 'Y';
            TDCUCACK.FOUND_FLG = 'Y';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void R000_CHECK_TDTCDI_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRCDI);
        TDRCDI.PAY_AC = TDCUCACK.AC_NO;
        T000_STARTBR_TDTCDI();
        if (pgmRtn) return;
        T000_READNEXT_TDTCDI();
        if (pgmRtn) return;
        while (WS_CDI_FLAG != 'N' 
            && WS_FOUND_FLG != 'Y') {
            WS_TD_AC = TDRCDI.KEY.ACO_AC;
            R000_CHECK_SMST_STS();
            if (pgmRtn) return;
            T000_READNEXT_TDTCDI();
            if (pgmRtn) return;
        }
        T000_ENDBR_TDTCDI();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CHECK TDTCDI END");
        CEP.TRC(SCCGWA, WS_FOUND_FLG);
        if (WS_FOUND_FLG == 'Y') {
            TDCUCACK.FOUND_FLG = 'Y';
            if (TDRSMST.STSW == null) TDRSMST.STSW = "";
            JIBS_tmp_int = TDRSMST.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
            if (TDRSMST.STSW.substring(21 - 1, 21 + 1 - 1).equalsIgnoreCase("1")) {
                TDCUCACK.GK_FLG = 'Y';
            }
            if (TDRSMST.STSW == null) TDRSMST.STSW = "";
            JIBS_tmp_int = TDRSMST.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
            if (TDRSMST.STSW.substring(22 - 1, 22 + 1 - 1).equalsIgnoreCase("1")) {
                TDCUCACK.PI_FLG = 'Y';
            }
            if (TDRSMST.STSW == null) TDRSMST.STSW = "";
            JIBS_tmp_int = TDRSMST.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
            if (TDRSMST.STSW.substring(23 - 1, 23 + 1 - 1).equalsIgnoreCase("1")) {
                TDCUCACK.FB_FLG = 'Y';
            }
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void R000_CHECK_TDTSMST_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRSMST);
        TDRSMST.ACO_STS = '0';
        T000_STARTBR_TDTSMST_FIRST();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CHECK TDTSMST END");
        CEP.TRC(SCCGWA, TDCUCACK.FOUND_FLG);
    }
    public void R000_CHECK_SMST_STS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRSMST);
        CEP.TRC(SCCGWA, WS_TD_AC);
        TDRSMST.KEY.ACO_AC = WS_TD_AC;
        TDRSMST.ACO_STS = '0';
        T000_READ_TDTSMST();
        if (pgmRtn) return;
    }
    public void T000_STARTBR_TDTINST() throws IOException,SQLException,Exception {
        WS_INST_FLAG = 'N';
        TDTINST_BR.rp = new DBParm();
        TDTINST_BR.rp.TableName = "TDTINST";
        TDTINST_BR.rp.where = "STL_AC = :TDRINST.STL_AC";
        IBS.STARTBR(SCCGWA, TDRINST, this, TDTINST_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_INST_FLAG = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_INST_FLAG = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "TDTINST";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_TDTINST_2() throws IOException,SQLException,Exception {
        WS_INST_FLAG = 'N';
        TDTINST_BR.rp = new DBParm();
        TDTINST_BR.rp.TableName = "TDTINST";
        TDTINST_BR.rp.where = "STL_INT_AC = :TDRINST.STL_INT_AC";
        IBS.STARTBR(SCCGWA, TDRINST, this, TDTINST_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_INST_FLAG = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_INST_FLAG = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "TDTINST";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READNEXT_TDTINST() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, TDRINST, this, TDTINST_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_INST_FLAG = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_INST_FLAG = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "TDTINST";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_TDTINST() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, TDTINST_BR);
    }
    public void T000_STARTBR_TDTCDI() throws IOException,SQLException,Exception {
        WS_CDI_FLAG = 'N';
        TDTCDI_BR.rp = new DBParm();
        TDTCDI_BR.rp.TableName = "TDTCDI";
        TDTCDI_BR.rp.where = "PAY_AC = :TDRCDI.PAY_AC";
        IBS.STARTBR(SCCGWA, TDRCDI, this, TDTCDI_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_CDI_FLAG = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_CDI_FLAG = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "TDTCDI";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READNEXT_TDTCDI() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, TDRCDI, this, TDTCDI_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_CDI_FLAG = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_CDI_FLAG = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "TDTCDI";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_TDTCDI() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, TDTCDI_BR);
    }
    public void T000_STARTBR_TDTSMST_FIRST() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        IBS.READ(SCCGWA, TDRSMST, TDTSMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            TDCUCACK.FOUND_FLG = 'N';
        } else {
            TDCUCACK.FOUND_FLG = 'Y';
        }
    }
    public void T000_READ_TDTSMST() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        TDTSMST_RD.eqWhere = "ACO_AC";
        TDTSMST_RD.where = "ACO_STS = :TDRSMST.ACO_STS";
        IBS.READ(SCCGWA, TDRSMST, this, TDTSMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FOUND_FLG = 'N';
        } else {
            WS_FOUND_FLG = 'Y';
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, SCCBINF);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
