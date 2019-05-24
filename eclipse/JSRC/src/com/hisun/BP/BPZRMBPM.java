package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRMBPM {
    String JIBS_tmp_str[] = new String[10];
    brParm BPTPARM_BR = new brParm();
    int JIBS_tmp_int;
    DBParm BPTPARM_RD;
    DBParm BPTPARP_RD;
    int WS_I = 0;
    int WS_LEN = 0;
    int WS_PARM_LEN = 0;
    BPZRMBPM_WS_PARM WS_PARM = new BPZRMBPM_WS_PARM();
    String WS_TYPE = " ";
    String WS_CODE = " ";
    String WS_BK = " ";
    char WS_FOUND_FLG = ' ';
    BPRPARM BPRPARM = new BPRPARM();
    BPRPARP BPRPARP = new BPRPARP();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCGWA SCCGWA;
    BPCRMBPM BPCRMBPM;
    SCCCWA SCCCWA;
    BPRPARM BPRLPARM;
    String LK_MMT = " ";
    public void MP(SCCGWA SCCGWA, BPCRMBPM BPCRMBPM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRMBPM = BPCRMBPM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZRMBPM return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRLPARM = (BPRPARM) BPCRMBPM.PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRMBPM.FUNC == 'S') {
            B001_STARTBR_TABLE();
        } else if (BPCRMBPM.FUNC == 'R') {
            B002_READNEXT_TABLE();
        } else if (BPCRMBPM.FUNC == 'E') {
            B003_ENDBR_TABLE();
        } else if (BPCRMBPM.FUNC == 'I') {
            B004_READ_TABLE();
        }
    }
    public void B001_STARTBR_TABLE() throws IOException,SQLException,Exception {
        T000_STARTBR_BPTPARM();
    }
    public void B002_READNEXT_TABLE() throws IOException,SQLException,Exception {
        T000_READNEXT_BPTPARM();
    }
    public void B003_ENDBR_TABLE() throws IOException,SQLException,Exception {
        T000_ENDBR_BPTPARM();
    }
    public void B004_READ_TABLE() throws IOException,SQLException,Exception {
        T000_READ_BPTPARM();
    }
    public void T000_STARTBR_BPTPARM() throws IOException,SQLException,Exception {
        WS_LEN = 48;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRLPARM);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPRPARM.KEY);
        WS_TYPE = BPRPARM.KEY.TYPE;
        WS_CODE = BPRPARM.KEY.CODE;
        WS_BK = BPRPARM.KEY.IBS_AC_BK;
        if (WS_BK.trim().length() == 0) {
            WS_BK = SCCGWA.COMM_AREA.TR_BANK;
        }
        CEP.TRC(SCCGWA, WS_BK);
        CEP.TRC(SCCGWA, BPRPARM.KEY.TYPE);
        CEP.TRC(SCCGWA, BPRPARM.KEY.CODE);
        CEP.TRC(SCCGWA, BPRPARM.KEY.IBS_AC_BK);
        BPTPARM_BR.rp = new DBParm();
        BPTPARM_BR.rp.TableName = "BPTPARM";
        BPTPARM_BR.rp.where = "TYPE = :WS_TYPE "
            + "AND CODE >= :WS_CODE "
            + "AND IBS_AC_BK = :WS_BK";
        BPTPARM_BR.rp.order = "CODE, EFF_DATE DESC";
        IBS.STARTBR(SCCGWA, BPRPARM, this, BPTPARM_BR);
        BPCRMBPM.RETURN_INFO = 'F';
    }
    public void T000_READNEXT_BPTPARM() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRPARM, this, BPTPARM_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRMBPM.RETURN_INFO = 'L';
        } else {
            BPCRMBPM.RETURN_INFO = 'F';
            WS_LEN = 192;
            IBS.CLONE(SCCGWA, BPRPARM, BPRLPARM);
        }
    }
    public void T000_ENDBR_BPTPARM() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTPARM_BR);
        BPCRMBPM.RETURN_INFO = 'F';
    }
    public void T000_READ_BPTPARM() throws IOException,SQLException,Exception {
        WS_LEN = 48;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRLPARM);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPRPARM.KEY);
        if (BPRPARM.KEY.IBS_AC_BK.trim().length() == 0) {
            BPRPARM.KEY.IBS_AC_BK = SCCGWA.COMM_AREA.TR_BANK;
        }
        IBS.init(SCCGWA, BPRPARP);
        BPRPARP.KEY.TYPE = BPRPARM.KEY.TYPE;
        T000_READ_BPTPARP();
        if (WS_FOUND_FLG != 'Y') {
            BPCRMBPM.RETURN_INFO = 'N';
        }
        if (BPRPARP.DUP_DATE_FLG == 'N') {
            if ("20000101".trim().length() == 0) BPRPARM.EFF_DATE = 0;
            else BPRPARM.EFF_DATE = Integer.parseInt("20000101");
        }
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        if (SCCGWA.COMM_AREA.PARM_IND == 'E') {
            WS_I = 9596;
            R000_SET_LK_MMT();
            WS_PARM = (BPZRMBPM_WS_PARM) IBS.HASHLKUP(SCCGWA, BPRPARM.KEY.TYPE, BPRPARM.KEY);
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                BPCRMBPM.RETURN_INFO = 'F';
                WS_I = WS_I - ( 9596 - 9500 );
                BPRPARM.EFF_DATE = WS_PARM.WS_EFF_DATE;
                BPRPARM.EXP_DATE = WS_PARM.WS_EXP_DATE;
                BPRPARM.DESC = WS_PARM.WS_DESC;
                BPRPARM.CDESC = WS_PARM.WS_CDESC;
                if (WS_PARM.WS_VAL_TEXT == null) WS_PARM.WS_VAL_TEXT = "";
                JIBS_tmp_int = WS_PARM.WS_VAL_TEXT.length();
                for (int i=0;i<9500-JIBS_tmp_int;i++) WS_PARM.WS_VAL_TEXT += " ";
                if (BPRPARM.BLOB_VAL == null) BPRPARM.BLOB_VAL = "";
                JIBS_tmp_int = BPRPARM.BLOB_VAL.length();
                for (int i=0;i<9500-JIBS_tmp_int;i++) BPRPARM.BLOB_VAL += " ";
                BPRPARM.BLOB_VAL = WS_PARM.WS_VAL_TEXT.substring(0, WS_I) + BPRPARM.BLOB_VAL.substring(WS_I);
                WS_LEN = 192;
                IBS.CLONE(SCCGWA, BPRPARM, BPRLPARM);
            } else {
                BPTPARM_RD = new DBParm();
                BPTPARM_RD.TableName = "BPTPARM";
                IBS.READ(SCCGWA, BPRPARM, BPTPARM_RD);
                if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                    BPCRMBPM.RETURN_INFO = 'N';
                } else {
                    BPCRMBPM.RETURN_INFO = 'F';
                    WS_LEN = 192;
                    IBS.CLONE(SCCGWA, BPRPARM, BPRLPARM);
                }
            }
        } else {
            BPTPARM_RD = new DBParm();
            BPTPARM_RD.TableName = "BPTPARM";
            IBS.READ(SCCGWA, BPRPARM, BPTPARM_RD);
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                BPCRMBPM.RETURN_INFO = 'N';
            } else {
                BPCRMBPM.RETURN_INFO = 'F';
                WS_LEN = 192;
                IBS.CLONE(SCCGWA, BPRPARM, BPRLPARM);
            }
        }
    } else { //FROM #ELSE
        BPTPARM_RD = new DBParm();
        BPTPARM_RD.TableName = "BPTPARM";
        IBS.READ(SCCGWA, BPRPARM, BPTPARM_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRMBPM.RETURN_INFO = 'N';
        } else {
            BPCRMBPM.RETURN_INFO = 'F';
            WS_LEN = 192;
            IBS.CLONE(SCCGWA, BPRPARM, BPRLPARM);
        }
    } //FROM #ENDIF
    }
    public void T000_READ_BPTPARP() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        if (SCCGWA.COMM_AREA.PARM_IND == 'E') {
            SCCCWA = (SCCCWA) SCCGWA.COMM_AREA.CWA_PTR;
            if (SCCCWA.PARM_IN_USE[7-1] == 1) {
                LK_MMT = IBS.CLS2CPY(SCCGWA, SCCCWA.PARM_PTR1_AREA[7-1].PARM_PTR1);
            } else {
                LK_MMT = IBS.CLS2CPY(SCCGWA, SCCCWA.PARM_PTR2_AREA[7-1].PARM_PTR2);
            }
            BPRPARP = (BPRPARP) IBS.HASHLKUP(SCCGWA, BPRPARP.KEY.TYPE, BPRPARP.KEY.TYPE);
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                WS_FOUND_FLG = 'Y';
            } else {
                BPTPARP_RD = new DBParm();
                BPTPARP_RD.TableName = "BPTPARP";
                IBS.READ(SCCGWA, BPRPARP, BPTPARP_RD);
                if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                    WS_FOUND_FLG = 'Y';
                } else {
                    WS_FOUND_FLG = ' ';
                }
            }
        } else {
            BPTPARP_RD = new DBParm();
            BPTPARP_RD.TableName = "BPTPARP";
            IBS.READ(SCCGWA, BPRPARP, BPTPARP_RD);
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                WS_FOUND_FLG = 'Y';
            } else {
                WS_FOUND_FLG = ' ';
            }
        }
    } else { //FROM #ELSE
        BPTPARP_RD = new DBParm();
        BPTPARP_RD.TableName = "BPTPARP";
        IBS.READ(SCCGWA, BPRPARP, BPTPARP_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FOUND_FLG = 'Y';
        } else {
            WS_FOUND_FLG = ' ';
        }
    } //FROM #ENDIF
    }
    public void R000_SET_LK_MMT() throws IOException,SQLException,Exception {
        SCCCWA = (SCCCWA) SCCGWA.COMM_AREA.CWA_PTR;
        if (BPRPARM.KEY.TYPE.equalsIgnoreCase("B")) {
            if (SCCCWA.PARM_IN_USE[1-1] == 1) {
                LK_MMT = IBS.CLS2CPY(SCCGWA, SCCCWA.PARM_PTR1_AREA[1-1].PARM_PTR1);
            } else {
                LK_MMT = IBS.CLS2CPY(SCCGWA, SCCCWA.PARM_PTR2_AREA[1-1].PARM_PTR2);
            }
        } else if (BPRPARM.KEY.TYPE.equalsIgnoreCase("CPN")) {
            if (SCCCWA.PARM_IN_USE[2-1] == 1) {
                LK_MMT = IBS.CLS2CPY(SCCGWA, SCCCWA.PARM_PTR1_AREA[2-1].PARM_PTR1);
            } else {
                LK_MMT = IBS.CLS2CPY(SCCGWA, SCCCWA.PARM_PTR2_AREA[2-1].PARM_PTR2);
            }
        } else if (BPRPARM.KEY.TYPE.equalsIgnoreCase("D")) {
            if (SCCCWA.PARM_IN_USE[3-1] == 1) {
                LK_MMT = IBS.CLS2CPY(SCCGWA, SCCCWA.PARM_PTR1_AREA[3-1].PARM_PTR1);
            } else {
                LK_MMT = IBS.CLS2CPY(SCCGWA, SCCCWA.PARM_PTR2_AREA[3-1].PARM_PTR2);
            }
        } else if (BPRPARM.KEY.TYPE.equalsIgnoreCase("I")) {
            if (SCCCWA.PARM_IN_USE[4-1] == 1) {
                LK_MMT = IBS.CLS2CPY(SCCGWA, SCCCWA.PARM_PTR1_AREA[4-1].PARM_PTR1);
            } else {
                LK_MMT = IBS.CLS2CPY(SCCGWA, SCCCWA.PARM_PTR2_AREA[4-1].PARM_PTR2);
            }
        } else if (BPRPARM.KEY.TYPE.equalsIgnoreCase("MSG")) {
            if (SCCCWA.PARM_IN_USE[5-1] == 1) {
                LK_MMT = IBS.CLS2CPY(SCCGWA, SCCCWA.PARM_PTR1_AREA[5-1].PARM_PTR1);
            } else {
                LK_MMT = IBS.CLS2CPY(SCCGWA, SCCCWA.PARM_PTR2_AREA[5-1].PARM_PTR2);
            }
        } else if (BPRPARM.KEY.TYPE.equalsIgnoreCase("MSGCK")) {
            if (SCCCWA.PARM_IN_USE[6-1] == 1) {
                LK_MMT = IBS.CLS2CPY(SCCGWA, SCCCWA.PARM_PTR1_AREA[6-1].PARM_PTR1);
            } else {
                LK_MMT = IBS.CLS2CPY(SCCGWA, SCCCWA.PARM_PTR2_AREA[6-1].PARM_PTR2);
            }
        } else if (BPRPARM.KEY.TYPE.equalsIgnoreCase("O")) {
            if (SCCCWA.PARM_IN_USE[7-1] == 1) {
                LK_MMT = IBS.CLS2CPY(SCCGWA, SCCCWA.PARM_PTR1_AREA[7-1].PARM_PTR1);
            } else {
                LK_MMT = IBS.CLS2CPY(SCCGWA, SCCCWA.PARM_PTR2_AREA[7-1].PARM_PTR2);
            }
        } else if (BPRPARM.KEY.TYPE.equalsIgnoreCase("PARMC")) {
            if (SCCCWA.PARM_IN_USE[8-1] == 1) {
                LK_MMT = IBS.CLS2CPY(SCCGWA, SCCCWA.PARM_PTR1_AREA[8-1].PARM_PTR1);
            } else {
                LK_MMT = IBS.CLS2CPY(SCCGWA, SCCCWA.PARM_PTR2_AREA[8-1].PARM_PTR2);
            }
        } else if (BPRPARM.KEY.TYPE.equalsIgnoreCase("PARMT")) {
            if (SCCCWA.PARM_IN_USE[8-1] == 1) {
                LK_MMT = IBS.CLS2CPY(SCCGWA, SCCCWA.PARM_PTR1_AREA[8-1].PARM_PTR1);
            } else {
                LK_MMT = IBS.CLS2CPY(SCCGWA, SCCCWA.PARM_PTR2_AREA[8-1].PARM_PTR2);
            }
        } else if (BPRPARM.KEY.TYPE.equalsIgnoreCase("TRT")) {
            if (SCCCWA.PARM_IN_USE[9-1] == 1) {
                LK_MMT = IBS.CLS2CPY(SCCGWA, SCCCWA.PARM_PTR1_AREA[9-1].PARM_PTR1);
            } else {
                LK_MMT = IBS.CLS2CPY(SCCGWA, SCCCWA.PARM_PTR2_AREA[9-1].PARM_PTR2);
            }
        } else if (BPRPARM.KEY.TYPE.equalsIgnoreCase("AMTL")) {
            if (SCCCWA.PARM_IN_USE[12-1] == 1) {
                LK_MMT = IBS.CLS2CPY(SCCGWA, SCCCWA.PARM_PTR1_AREA[12-1].PARM_PTR1);
            } else {
                LK_MMT = IBS.CLS2CPY(SCCGWA, SCCCWA.PARM_PTR2_AREA[12-1].PARM_PTR2);
            }
        } else if (BPRPARM.KEY.TYPE.equalsIgnoreCase("ORGM")) {
            if (SCCCWA.PARM_IN_USE[12-1] == 1) {
                LK_MMT = IBS.CLS2CPY(SCCGWA, SCCCWA.PARM_PTR1_AREA[12-1].PARM_PTR1);
            } else {
                LK_MMT = IBS.CLS2CPY(SCCGWA, SCCCWA.PARM_PTR2_AREA[12-1].PARM_PTR2);
            }
        } else if (BPRPARM.KEY.TYPE.equalsIgnoreCase("TIRUL")) {
            if (SCCCWA.PARM_IN_USE[2-1] == 1) {
                LK_MMT = IBS.CLS2CPY(SCCGWA, SCCCWA.PARM_PTR1_AREA[2-1].PARM_PTR1);
            } else {
                LK_MMT = IBS.CLS2CPY(SCCGWA, SCCCWA.PARM_PTR2_AREA[2-1].PARM_PTR2);
            }
        } else {
            if (SCCCWA.PARM_IN_USE[10-1] == 1) {
                LK_MMT = IBS.CLS2CPY(SCCGWA, SCCCWA.PARM_PTR1_AREA[10-1].PARM_PTR1);
            } else {
                LK_MMT = IBS.CLS2CPY(SCCGWA, SCCCWA.PARM_PTR2_AREA[10-1].PARM_PTR2);
            }
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRMBPM.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCRMBPM = ");
            CEP.TRC(SCCGWA, BPCRMBPM);
        }
    } //FROM #ENDIF
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
