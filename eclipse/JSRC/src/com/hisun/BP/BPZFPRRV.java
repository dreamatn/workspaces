package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZFPRRV {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_R_MGM_GRPL = "BP-R-MGM-GRPL       ";
    String CPN_R_MGM_TATH = "BP-R-MGM-TATH       ";
    String CPN_R_MGM_TATB = "BP-R-MGM-TATB       ";
    String CPN_R_MGM_GPLB = "BP-R-MGM-GPLB       ";
    char WS_ATH_TYP = ' ';
    short WS_I = 0;
    char WS_TBL_GRP_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    BPRGRPL BPRGRPL = new BPRGRPL();
    BPRTATH BPRTATH = new BPRTATH();
    BPCRGRPL BPCRGRPL = new BPCRGRPL();
    BPCRTATH BPCRTATH = new BPCRTATH();
    BPCRTATB BPCRTATB = new BPCRTATB();
    BPCRGPLB BPCRGPLB = new BPCRGPLB();
    SCCGWA SCCGWA;
    BPCFPRRV BPCFPRRV;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCFPRRV BPCFPRRV) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCFPRRV = BPCFPRRV;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZFPRRV return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPRGRPL);
        IBS.init(SCCGWA, BPRTATH);
        IBS.init(SCCGWA, BPCRGRPL);
        IBS.init(SCCGWA, BPCRTATH);
        IBS.init(SCCGWA, BPCRTATB);
        IBS.init(SCCGWA, BPCRGPLB);
        IBS.CPY2CLS(SCCGWA, "BP0000", BPCFPRRV.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_REVOKE_OPER_PRIV();
        if (pgmRtn) return;
        B030_REVOKE_AUTH_PRIV();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCFPRRV);
        if ((BPCFPRRV.ASSTYP != 'O' 
            && BPCFPRRV.ASSTYP != 'T')) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_FPRRV_ASSTYP, BPCFPRRV.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCFPRRV.ASS_ID.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_FPRRV_ASS_ID, BPCFPRRV.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_REVOKE_OPER_PRIV() throws IOException,SQLException,Exception {
        WS_ATH_TYP = '0';
        R_REVOKE_GRPL();
        if (pgmRtn) return;
        R_REVOKE_TATH();
        if (pgmRtn) return;
    }
    public void B030_REVOKE_AUTH_PRIV() throws IOException,SQLException,Exception {
        WS_ATH_TYP = '1';
        R_REVOKE_GRPL();
        if (pgmRtn) return;
        R_REVOKE_TATH();
        if (pgmRtn) return;
    }
    public void R_REVOKE_GRPL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRGRPL);
        IBS.init(SCCGWA, BPRGRPL);
        BPCRGRPL.INFO.FUNC = 'R';
        BPRGRPL.KEY.ASS_TYP = BPCFPRRV.ASSTYP;
        BPRGRPL.KEY.ASS_ID = BPCFPRRV.ASS_ID;
        BPRGRPL.KEY.ATH_TYP = WS_ATH_TYP;
        S000_CALL_BPZRGRPL();
        if (pgmRtn) return;
        if (BPCRGRPL.RETURN_INFO == 'F') {
            CEP.TRC(SCCGWA, BPRGRPL.ROLE_CNT);
            for (WS_I = 1; WS_I <= BPRGRPL.ROLE_CNT; WS_I += 1) {
                if (BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].EFF_DT > SCCGWA.COMM_AREA.AC_DATE) {
                    BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].EFF_DT = SCCGWA.COMM_AREA.AC_DATE;
                    BPRGRPL.REDEFINES14.ROLE_TXT_TEXT1 = IBS.CLS2CPY(SCCGWA, BPRGRPL.REDEFINES14.REDEFINES16);
                }
                BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].EXP_DT = SCCGWA.COMM_AREA.AC_DATE;
                BPRGRPL.REDEFINES14.ROLE_TXT_TEXT1 = IBS.CLS2CPY(SCCGWA, BPRGRPL.REDEFINES14.REDEFINES16);
            }
            BPRGRPL.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
            BPRGRPL.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
            IBS.init(SCCGWA, BPCRGRPL);
            BPCRGRPL.INFO.FUNC = 'U';
            S000_CALL_BPZRGRPL();
            if (pgmRtn) return;
        }
    }
    public void R_REVOKE_TATH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRTATH);
        IBS.init(SCCGWA, BPRTATH);
        BPCRTATH.INFO.FUNC = 'R';
        BPRTATH.KEY.ASS_TYP = BPCFPRRV.ASSTYP;
        BPRTATH.KEY.ASS_ID = BPCFPRRV.ASS_ID;
        BPRTATH.KEY.ATH_TYP = WS_ATH_TYP;
        S000_CALL_BPZRTATH();
        if (pgmRtn) return;
        if (BPCRTATH.RETURN_INFO == 'F') {
            for (WS_I = 1; WS_I <= BPRTATH.TXIF_CNT; WS_I += 1) {
                if (BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].EFF_DT > SCCGWA.COMM_AREA.AC_DATE) {
                    BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].EFF_DT = SCCGWA.COMM_AREA.AC_DATE;
                    BPRTATH.REDEFINES14.TXIF_TXT_TEXT1 = IBS.CLS2CPY(SCCGWA, BPRTATH.REDEFINES14.REDEFINES16);
                }
                BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].EXP_DT = SCCGWA.COMM_AREA.AC_DATE;
                BPRTATH.REDEFINES14.TXIF_TXT_TEXT1 = IBS.CLS2CPY(SCCGWA, BPRTATH.REDEFINES14.REDEFINES16);
            }
            BPRTATH.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
            BPRTATH.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
            IBS.init(SCCGWA, BPCRTATH);
            BPCRTATH.INFO.FUNC = 'U';
            S000_CALL_BPZRTATH();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRGRPL() throws IOException,SQLException,Exception {
        BPCRGRPL.INFO.POINTER = BPRGRPL;
        BPCRGRPL.INFO.LEN = 52;
        CEP.TRC(SCCGWA, BPRGRPL.BLOB_ROLE_TXT.trim().length());
        IBS.CALLCPN(SCCGWA, "BP-R-MGM-GRPL       ", BPCRGRPL);
        CEP.TRC(SCCGWA, BPCRGRPL.RC);
        if (BPCRGRPL.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRGRPL.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCFPRRV.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRGPLB() throws IOException,SQLException,Exception {
        BPCRGPLB.INFO.POINTER = BPRGRPL;
        BPCRGPLB.INFO.LEN = 52;
        IBS.CALLCPN(SCCGWA, "BP-R-MGM-GPLB       ", BPCRGPLB);
        if (BPCRGPLB.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRGPLB.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCFPRRV.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRTATH() throws IOException,SQLException,Exception {
        BPCRTATH.INFO.POINTER = BPRTATH;
        BPCRTATH.INFO.LEN = 52;
        IBS.CALLCPN(SCCGWA, "BP-R-MGM-TATH       ", BPCRTATH);
        if (BPCRTATH.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRTATH.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCFPRRV.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRTATB() throws IOException,SQLException,Exception {
        BPCRTATB.INFO.POINTER = BPRTATH;
        BPCRTATB.INFO.LEN = 52;
        IBS.CALLCPN(SCCGWA, "BP-R-MGM-TATB       ", BPCRTATB);
        if (BPCRTATB.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRTATB.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCFPRRV.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCFPRRV.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCFPRRV = ");
            CEP.TRC(SCCGWA, BPCFPRRV);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
