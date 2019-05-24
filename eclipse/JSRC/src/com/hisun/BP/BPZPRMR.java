package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;
import com.hisun.SC.*;

public class BPZPRMR {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    DBParm BPTPARM_RD;
    BPZPRMR_WS_VARIABLES WS_VARIABLES = new BPZPRMR_WS_VARIABLES();
    BPZPRMR_WS_PARM WS_PARM = new BPZPRMR_WS_PARM();
    char WS_MMT_INIT_FLG = ' ';
    String WS_MMT = " ";
    BPRPARM BPRPARM = new BPRPARM();
    BPCMSG_ERROR_MSG ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCGWA SCCGWA;
    BPCPRMR BPCPRMR;
    BPRPRMT BPRPRMT = new BPRPRMT();
    SCCCWA SCCCWA;
    String LK_MMT = " ";
    public void MP(SCCGWA SCCGWA, BPCPRMR BPCPRMR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCPRMR = BPCPRMR;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZPRMR return!");
        Z_RET();
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_VARIABLES);
        BPCPRMR.RC.RC_APP = "BP";
        BPCPRMR.RC.RC_RTNCODE = 0;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMR.DAT_PTR);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPRPRMT);
        if (BPCPRMR.TYP.trim().length() == 0) {
            BPCPRMR.TYP = BPRPRMT.KEY.TYP;
            BPCPRMR.CD = BPRPRMT.KEY.CD;
            BPCPRMR.BK = BPRPRMT.KEY.BK;
        } else {
            BPRPRMT.KEY.TYP = BPCPRMR.TYP;
            BPRPRMT.KEY.CD = BPCPRMR.CD;
            BPRPRMT.KEY.BK = BPCPRMR.BK;
        }
        if (BPRPRMT.KEY.BK.trim().length() == 0) {
            BPRPRMT.KEY.BK = SCCGWA.COMM_AREA.TR_BANK;
        }
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        if (SCCGWA.COMM_AREA.PARM_IND == 'E') {
            B100_READ_MMT();
            if (WS_VARIABLES.FOUND_FLG != 'Y') {
                B200_READ_DB();
            }
        } else {
            B200_READ_DB();
        }
    } else { //FROM #ELSE
        if (WS_MMT_INIT_FLG != 'Y') {
            R000_INIT_MMT();
            WS_MMT_INIT_FLG = 'Y';
        }
        B100_READ_MMT();
        if (WS_VARIABLES.FOUND_FLG != 'Y') {
            B200_READ_DB();
        }
    } //FROM #ENDIF
        if (WS_VARIABLES.FOUND_FLG != 'Y') {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.BP_PARM_NOTFND, BPCPRMR.RC);
        }
    }
    public void B100_READ_MMT() throws IOException,SQLException,Exception {
        WS_VARIABLES.I = 9596;
        R000_SET_LK_MMT();
        SCCGWA.COMM_AREA.DBIO_FLG = '0';
        WS_PARM = (BPZPRMR_WS_PARM) IBS.HASHLKUP(SCCGWA, BPRPARM.KEY.TYPE, BPRPRMT.KEY);
        CEP.TRC(SCCGWA, BPRPRMT.KEY);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_VARIABLES.I = WS_VARIABLES.I - ( 9596 - 9500 );
            BPRPRMT.DESC = WS_PARM.DESC;
            BPRPRMT.CDESC = WS_PARM.CDESC;
            if (WS_PARM.VAL_TEXT == null) WS_PARM.VAL_TEXT = "";
            JIBS_tmp_int = WS_PARM.VAL_TEXT.length();
            for (int i=0;i<9500-JIBS_tmp_int;i++) WS_PARM.VAL_TEXT += " ";
            JIBS_tmp_str[0] = WS_PARM.VAL_TEXT.substring(0, WS_VARIABLES.I);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPRPRMT.DAT_TXT);
            WS_VARIABLES.FOUND_FLG = 'Y';
            CEP.TRC(SCCGWA, WS_PARM.VAL_TEXT);
            CEP.TRC(SCCGWA, WS_VARIABLES.I);
        }
    }
    public void B200_READ_DB() throws IOException,SQLException,Exception {
        BPRPARM.KEY.TYPE = BPRPRMT.KEY.TYP;
        BPRPARM.KEY.CODE = BPRPRMT.KEY.CD;
        BPRPARM.KEY.IBS_AC_BK = BPRPRMT.KEY.BK;
        CEP.TRC(SCCGWA, BPRPARM.KEY);
        T000_READ_BPTPARM();
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPRPRMT.DESC = BPRPARM.DESC;
            BPRPRMT.CDESC = BPRPARM.CDESC;
            if (BPRPARM.BLOB_VAL.trim().length() > 0) {
                JIBS_tmp_str[0] = BPRPARM.BLOB_VAL;
                IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPRPRMT.DAT_TXT);
            }
            CEP.TRC(SCCGWA, BPRPRMT.DAT_TXT);
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
            R000_LOAD_REC();
    } //FROM #ENDIF
            WS_VARIABLES.FOUND_FLG = 'Y';
        }
    }
    //if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
    public void R000_INIT_MMT() throws IOException,SQLException,Exception {
        WS_VARIABLES.MMT_SIZE = 16000000;
        WS_VARIABLES.I = WS_VARIABLES.MMT_SIZE / 250;
    }
    public void R000_LOAD_REC() throws IOException,SQLException,Exception {
        WS_PARM.DESC = BPRPARM.DESC;
        WS_PARM.CDESC = BPRPARM.CDESC;
        if (BPRPARM.BLOB_VAL.trim().length() > 0) {
            if (WS_PARM.VAL_TEXT == null) WS_PARM.VAL_TEXT = "";
            JIBS_tmp_int = WS_PARM.VAL_TEXT.length();
            for (int i=0;i<9500-JIBS_tmp_int;i++) WS_PARM.VAL_TEXT += " ";
            if (BPRPARM.BLOB_VAL == null) BPRPARM.BLOB_VAL = "";
            JIBS_tmp_int = BPRPARM.BLOB_VAL.length();
            for (int i=0;i<9500-JIBS_tmp_int;i++) BPRPARM.BLOB_VAL += " ";
            WS_PARM.VAL_TEXT = BPRPARM.BLOB_VAL + WS_PARM.VAL_TEXT.substring(BPRPARM.BLOB_VAL.length());
        }
        WS_VARIABLES.I = 9596 - 9500 + BPRPARM.BLOB_VAL.trim().length();
        IBS.HASHADD(SCCGWA, BPRPARM.KEY.TYPE, BPRPARM.KEY, WS_PARM);
    }
    //} //FROM #ENDIF
    public void T000_READ_BPTPARM() throws IOException,SQLException,Exception {
        BPRPARM.EFF_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPRPARM.EXP_DATE = SCCGWA.COMM_AREA.AC_DATE;
        CEP.TRC(SCCGWA, BPRPARM.KEY.TYPE);
        CEP.TRC(SCCGWA, BPRPARM.KEY.CODE);
        CEP.TRC(SCCGWA, BPRPARM.EFF_DATE);
        CEP.TRC(SCCGWA, BPRPARM.EXP_DATE);
        BPTPARM_RD = new DBParm();
        BPTPARM_RD.TableName = "BPTPARM";
        BPTPARM_RD.eqWhere = "TYPE, CODE,IBS_AC_BK";
        BPTPARM_RD.where = "EFF_DATE <= :BPRPARM.EFF_DATE "
            + "AND EXP_DATE > :BPRPARM.EFF_DATE";
        IBS.READ(SCCGWA, BPRPARM, this, BPTPARM_RD);
    }
    public void R000_SET_LK_MMT() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        LK_MMT = WS_MMT;
    } else { //FROM #ELSE
        SCCCWA = (SCCCWA) SCCGWA.COMM_AREA.CWA_PTR;
        if (BPRPRMT.KEY.TYP.equalsIgnoreCase("B")) {
            WS_VARIABLES.I = 1;
        } else if (BPRPRMT.KEY.TYP.equalsIgnoreCase("CPN")
            || BPRPRMT.KEY.TYP.equalsIgnoreCase("TIRUL")) {
            WS_VARIABLES.I = 2;
        } else if (BPRPRMT.KEY.TYP.equalsIgnoreCase("D")) {
            WS_VARIABLES.I = 3;
        } else if (BPRPRMT.KEY.TYP.equalsIgnoreCase("I")) {
            WS_VARIABLES.I = 4;
        } else if (BPRPRMT.KEY.TYP.equalsIgnoreCase("MSG")) {
            WS_VARIABLES.I = 5;
        } else if (BPRPRMT.KEY.TYP.equalsIgnoreCase("MSGCK")) {
            WS_VARIABLES.I = 6;
        } else if (BPRPRMT.KEY.TYP.equalsIgnoreCase("O")) {
            WS_VARIABLES.I = 7;
        } else if (BPRPRMT.KEY.TYP.equalsIgnoreCase("PARMC")
            || BPRPRMT.KEY.TYP.equalsIgnoreCase("PARMT")) {
            WS_VARIABLES.I = 8;
        } else if (BPRPRMT.KEY.TYP.equalsIgnoreCase("TRT")) {
            WS_VARIABLES.I = 9;
        } else if (BPRPRMT.KEY.TYP.equalsIgnoreCase("AMTL")
            || BPRPRMT.KEY.TYP.equalsIgnoreCase("ORGM")) {
            WS_VARIABLES.I = 12;
        } else {
            WS_VARIABLES.I = 10;
        }
        CEP.TRC(SCCGWA, WS_VARIABLES.I);
        CEP.TRC(SCCGWA, SCCCWA.PARM_IN_USE[WS_VARIABLES.I-1]);
        CEP.TRC(SCCGWA, SCCCWA.PARM_PTR1_AREA[WS_VARIABLES.I-1].PARM_PTR1);
        CEP.TRC(SCCGWA, SCCCWA.PARM_PTR2_AREA[WS_VARIABLES.I-1].PARM_PTR2);
        if (SCCCWA.PARM_IN_USE[WS_VARIABLES.I-1] == 1) {
            LK_MMT = IBS.CLS2CPY(SCCGWA, SCCCWA.PARM_PTR1_AREA[WS_VARIABLES.I-1].PARM_PTR1);
        } else {
            LK_MMT = IBS.CLS2CPY(SCCGWA, SCCCWA.PARM_PTR2_AREA[WS_VARIABLES.I-1].PARM_PTR2);
        }
    } //FROM #ENDIF
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void JIBS_RETURN() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRPRMT);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCPRMR.DAT_PTR);
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
