package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZOSTNO {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_INQ_PUB_PARM = "BP-PARM-READ      ";
    String CPN_P_INQ_PC = "BP-P-INQ-PC         ";
    char WS_NEXT_FLG = ' ';
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPZOSTNO_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new BPZOSTNO_WS_TEMP_VARIABLE();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPRPARM BPRPARM = new BPRPARM();
    BPREXRT BPREXRT = new BPREXRT();
    BPROSTNO BPROSTNO = new BPROSTNO();
    BPCOQPCD BPCOQPCD = new BPCOQPCD();
    BPCRMBPM BPCRMBPM = new BPCRMBPM();
    SCCGWA SCCGWA;
    BPCOSTNO BPCOSTNO;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCOSTNO BPCOSTNO) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCOSTNO = BPCOSTNO;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZOSTNO return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, BPCRMBPM);
        BPCRMBPM.RC.RC_MMO = "BP";
        BPCRMBPM.PTR = BPRPARM;
        IBS.init(SCCGWA, BPROSTNO);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCOSTNO.FUNC == 'A') {
            B010_CHECK_INPUT();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPCOSTNO.VAL == null) BPCOSTNO.VAL = "";
        JIBS_tmp_int = BPCOSTNO.VAL.length();
        for (int i=0;i<9500-JIBS_tmp_int;i++) BPCOSTNO.VAL += " ";
        IBS.CPY2CLS(SCCGWA, BPCOSTNO.VAL.substring(0, BPCOSTNO.LEN), BPROSTNO.DATA_TXT);
        IBS.init(SCCGWA, BPCOQPCD);
        BPCOQPCD.INPUT_DATA.TYPE = "OSTNO";
        BPCOQPCD.INPUT_DATA.CODE = BPCOSTNO.CODE;
        CEP.TRC(SCCGWA, BPCOQPCD.INPUT_DATA.CODE);
        S000_CALL_BPZPQPCD();
        if (pgmRtn) return;
        BPCOQPCD.INPUT_DATA.TYPE = "RTENO";
        BPCOQPCD.INPUT_DATA.CODE = BPROSTNO.DATA_TXT.TENOR_TY;
        CEP.TRC(SCCGWA, BPCOQPCD.INPUT_DATA.CODE);
        S000_CALL_BPZPQPCD();
        if (pgmRtn) return;
        B010_STARTBR_PARM();
        if (pgmRtn) return;
        B015_READNEXT_PARM();
        if (pgmRtn) return;
        WS_NEXT_FLG = 'Y';
        while (BPCRMBPM.RETURN_INFO != 'L' 
            && WS_NEXT_FLG != 'N') {
            CEP.TRC(SCCGWA, BPRPARM.KEY.CODE);
            if (BPRPARM.BLOB_VAL == null) BPRPARM.BLOB_VAL = "";
            JIBS_tmp_int = BPRPARM.BLOB_VAL.length();
            for (int i=0;i<9500-JIBS_tmp_int;i++) BPRPARM.BLOB_VAL += " ";
            CEP.TRC(SCCGWA, BPRPARM.BLOB_VAL.substring(0, 3));
            if (BPCOSTNO.TYPE.equalsIgnoreCase(BPRPARM.KEY.TYPE) 
                && BPCOSTNO.CODE.equalsIgnoreCase(BPRPARM.KEY.CODE)) {
                WS_NEXT_FLG = 'Y';
            } else {
                if (BPRPARM.BLOB_VAL == null) BPRPARM.BLOB_VAL = "";
                JIBS_tmp_int = BPRPARM.BLOB_VAL.length();
                for (int i=0;i<9500-JIBS_tmp_int;i++) BPRPARM.BLOB_VAL += " ";
                if (BPROSTNO.DATA_TXT.TENOR_TY.equalsIgnoreCase(BPRPARM.BLOB_VAL.substring(0, 3))) {
                    WS_NEXT_FLG = 'N';
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TENOR_ALREADY_EXIST;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                } else {
                    WS_NEXT_FLG = 'Y';
                }
            }
            B015_READNEXT_PARM();
            if (pgmRtn) return;
        }
    }
    public void B010_STARTBR_PARM() throws IOException,SQLException,Exception {
        BPCRMBPM.FUNC = 'S';
        BPRPARM.KEY.TYPE = "OSTNO";
        CEP.TRC(SCCGWA, BPRPARM.KEY.TYPE);
        S000_CALL_BPZRMBPM();
        if (pgmRtn) return;
    }
    public void B015_READNEXT_PARM() throws IOException,SQLException,Exception {
        BPCRMBPM.FUNC = 'R';
        S000_CALL_BPZRMBPM();
        if (pgmRtn) return;
    }
    public void B020_ENDBR_PARM() throws IOException,SQLException,Exception {
        BPCRMBPM.FUNC = 'E';
        S000_CALL_BPZRMBPM();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZRMBPM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-MBRW-PARM", BPCRMBPM);
        if (BPCRMBPM.RC.RC_CODE != 0) {
            WS_TEMP_VARIABLE.WS_MSGID = IBS.CLS2CPY(SCCGWA, BPCRMBPM.RC);
            CEP.ERR(SCCGWA, WS_TEMP_VARIABLE.WS_MSGID);
        }
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        BPCPRMR.DAT_PTR = BPREXRT;
        IBS.CALLCPN(SCCGWA, CPN_INQ_PUB_PARM, BPCPRMR);
    }
    public void S000_CALL_BPZPQPCD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_PC, BPCOQPCD);
        if (BPCOQPCD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCOQPCD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void B_MPAG() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        JIBS_tmp_str[9] = "SCZMPAG";
        Class<?>clazz = Class.forName(JIBS_tmp_str[9].trim());
        Object obj = clazz.newInstance();
        Method m = clazz.getDeclaredMethod("MP",new Class[]{SCCGWA.getClass(), SCCMPAG.getClass()});
        m.invoke(obj, SCCGWA, SCCMPAG);
        if (SCCGWA.COMM_AREA.EXCP_FLG == 'Y') {
            Z_RET();
            if (pgmRtn) return;
        }
    } else { //FROM #ELSE
    } //FROM #ENDIF
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCOSTNO.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCOSTNO = ");
            CEP.TRC(SCCGWA, BPCOSTNO);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
